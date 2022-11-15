package com.eduside.smartgoat.data.local.sp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormatDataLogin(
    val email: String? = "",
    val password: String? = ""
): Parcelable