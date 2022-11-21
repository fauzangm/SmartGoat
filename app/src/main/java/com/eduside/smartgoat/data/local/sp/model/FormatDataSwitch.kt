package com.eduside.smartgoat.data.local.sp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormatDataSwitch(
    val modeTimbangan:Boolean? = false,
    val modeLampu:Boolean? = false,
    val modeFan:Boolean? =false,
    val currentDimmer:Int? = 0
): Parcelable