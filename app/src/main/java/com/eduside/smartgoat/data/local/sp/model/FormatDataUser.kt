package com.eduside.smartgoat.data.local.sp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormatDataUser(
    val name:String? = "",
    val email:String? = "",
    val ttl:String? ="",
    val domisili:String? = "",
    val lokasi_kandang:String? = ""
): Parcelable