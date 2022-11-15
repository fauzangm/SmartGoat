package com.eduside.smartgoat.data.remote.model

import android.os.Parcelable
import com.eduside.smartgoat.util.UNKNOWN_NETWORK_ERROR
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Error(
    @SerializedName("message") val message: String?
) : Parcelable {

    companion object {
        fun getErrorMessage(jsonString: String): String {
            val error = Gson().fromJson(jsonString, Error::class.java)
            return when {
                !error.message.isNullOrBlank() -> error.message
                else -> UNKNOWN_NETWORK_ERROR
            }
        }
    }
}