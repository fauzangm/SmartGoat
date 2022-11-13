package com.eduside.bappenda.util

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Base64
import com.eduside.smartgoat.R
import com.eduside.smartgoat.util.MAX_IMAGE_SIZE
import com.eduside.smartgoat.util.MAX_IMAGE_WEIGHT
import timber.log.Timber
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


private const val FILENAME_FORMAT = "dd-MMM-yyyy"

val timeStamp: String = SimpleDateFormat(
    FILENAME_FORMAT,
    Locale.US
).format(System.currentTimeMillis())

fun createCustomTempFile(context: Context): File {
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(timeStamp, ".jpg", storageDir)
}

fun createFile(application: Application): File {
    val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
        File(it, application.resources.getString(R.string.app_name)).apply { mkdirs() }
    }

    val outputDirectory = if (
        mediaDir != null && mediaDir.exists()
    ) mediaDir else application.filesDir

    return File(outputDirectory, "$timeStamp.jpg")
}

fun rotateBitmap(bitmap: Bitmap, isBackCamera: Boolean = true): Bitmap {
    val matrix = Matrix()
    return if (isBackCamera) {
        matrix.postRotate(90f)
        Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    } else {
        matrix.postRotate(-90f)
        matrix.postScale(-1f, 1f, bitmap.width / 2f, bitmap.height / 2f)
        Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.width,
            bitmap.height,
            matrix,
            true
        )
    }
}

fun uriToFile(selectedImg: Uri, context: Context): File {
    val contentResolver: ContentResolver = context.contentResolver
    val myFile = createFile(context.applicationContext as Application)

    val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
    val outputStream: OutputStream = FileOutputStream(myFile)
    val buf = ByteArray(1024)
    var len: Int
    while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
    outputStream.close()
    inputStream.close()

    return myFile
}

fun convertBase64ToBitmap(base64: String): Bitmap {
    val imageBytes: ByteArray = Base64.decode(base64, Base64.NO_WRAP)
    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
}

fun resizeAndCompressImageBeforeSend(
    context: Context,
    sourceFile: String,
    destFile: String? = null,
    rotateImage: Boolean = false,
    isBackCamera: Boolean = true
): String {

    var bmpStream: ByteArrayOutputStream
    var bmpPicByteArray: ByteArray

    // First decode with inJustDecodeBounds=true to check dimensions of image
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(sourceFile, options)

    // Calculate inSampleSize(First we are going to resize the image to 800x800 image, in order to not have a big but very low quality image.
    // resizing the image will already reduce the file size, but after resizing we will check the file size and start to compress image
    options.inSampleSize = calculateInSampleSize(options, MAX_IMAGE_SIZE, MAX_IMAGE_SIZE)

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false
    options.inPreferredConfig = Bitmap.Config.ARGB_8888

    var bmpPic: Bitmap? = null
    if (Build.VERSION.SDK_INT >= 29) {
        val imageUri: Uri = Uri.fromFile(File(sourceFile))
        try {
            context.contentResolver.openFileDescriptor(imageUri, "r").use { pfd ->
                if (pfd != null) {
                    bmpPic = scaleDown(BitmapFactory.decodeFileDescriptor(pfd.fileDescriptor))
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    } else {
        bmpPic = scaleDown(BitmapFactory.decodeFile(sourceFile, options))
    }

    if(rotateImage) bmpPic?.let { bmpPic = rotateBitmap(it, isBackCamera) }

    var compressQuality = 100 // quality decreasing by 5 every loop.
    var streamLength: Int
    do {
        bmpStream = ByteArrayOutputStream()
        bmpPic?.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
        bmpPicByteArray = bmpStream.toByteArray()
        streamLength = bmpPicByteArray.size
        compressQuality -= 5
        Timber.d("Quality: $compressQuality")
        Timber.d("Size: " + streamLength / 1024 + " kb")
    } while (compressQuality > 0 && streamLength >= (MAX_IMAGE_WEIGHT * 1024))

    if(!destFile.isNullOrBlank()) {
        try {
            //save the resized and compressed file
            val bmpFile = FileOutputStream(File(destFile))
            bmpPic?.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpFile)
            bmpFile.flush()
            bmpFile.close()
        } catch (e: Exception) {
            Timber.e("Error on saving file because ${e.localizedMessage}")
            e.printStackTrace()
        }
    }

    //return the path of resized and compressed file
    Timber.d("file compressed: $destFile")
    return Base64.encodeToString(bmpPicByteArray, Base64.NO_WRAP)
}

private fun calculateInSampleSize(
    options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int
): Int {
    var inSampleSize = 1
    try {
        val height = options.outHeight
        val width = options.outWidth
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return inSampleSize
}

private fun scaleDown(
    realImage: Bitmap
): Bitmap {
    val maxImageSize = MAX_IMAGE_SIZE.toFloat()
    val ratio = (maxImageSize / realImage.width).coerceAtMost(maxImageSize / realImage.height)
    val width = (ratio * realImage.width).roundToInt()
    val height = (ratio * realImage.height).roundToInt()

    return Bitmap.createScaledBitmap(
        realImage, width,
        height, true
    )
}

fun deleteTempFile(path: String) {
    if (path.isNotBlank()) {
        val fileToDelete = File(path)
        fileToDelete.delete()
    }
}