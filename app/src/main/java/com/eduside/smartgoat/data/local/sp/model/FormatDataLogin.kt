package com.eduside.smartgoat.data.local.sp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormatDataLogin(
    val name: String? = "",
    val password: String? = ""
): Parcelable