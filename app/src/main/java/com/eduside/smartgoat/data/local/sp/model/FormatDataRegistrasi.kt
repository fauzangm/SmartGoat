package com.eduside.smartgoat.data.local.sp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormatDataRegistrasi(
    val email: String? = "",
    val ttl: String? = "",
    val lokasi_kandang: String? = "",
    val domisili: String? = "",
    val nodeid: Int? = 0,
    val name: String? = "",
    val password: String? = "",
    val password_confirmation: String? = "",
): Parcelable


