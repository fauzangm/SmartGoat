package com.eduside.smartgoat.util

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Base64
import android.webkit.MimeTypeMap
import com.eduside.smartgoat.R
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

fun getJSONFromAssets(context: Context, file: Int): String? {

    var json: String? = null
    val charset: Charset = Charsets.UTF_8
    try {
        val jsonFile = context.resources.openRawResource(file)
        val size = jsonFile.available()
        val buffer = ByteArray(size)
        jsonFile.read(buffer)
        jsonFile.close()
        json = String(buffer, charset)
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }
    return json
}

fun getExtension(context: Context, fileUri: Uri): String? {
    return if (fileUri.scheme == ContentResolver.SCHEME_CONTENT) {
        val mime = MimeTypeMap.getSingleton()
        mime.getExtensionFromMimeType(context.contentResolver.getType(fileUri))
    } else {
        MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(fileUri.path?.let { File(it) }).toString())
    }
}

fun convertUriToBase64(context: Context, uri: Uri): String {
    var base64 = ""
    try {
        val contentResolver = context.contentResolver
        val bytes = contentResolver.openInputStream(uri)
        base64 = Base64.encodeToString(bytes?.readBytes(), Base64.NO_WRAP)
        bytes?.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return base64
}

fun getFileInfoFromUri(context: Context, uri: Uri): FileInfo {
    var fileName = ""
    var fileSIze = 0
    try {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        fileName = cursor?.getString(if(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME) >= 0) {
            cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        } else {
            0
        }).toString()
        fileSIze = cursor?.getLong(if(cursor.getColumnIndex(OpenableColumns.SIZE) >= 0) {
            cursor.getColumnIndex(OpenableColumns.SIZE)
        } else {
            0
        })?.toInt() ?: 0
        cursor?.close( )
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return FileInfo(fileName, fileSIze)
}

data class FileInfo(
    val filename: String,
    val filesize: Int
)