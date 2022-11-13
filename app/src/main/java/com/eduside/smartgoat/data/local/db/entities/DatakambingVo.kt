package com.eduside.smartgoat.data.local.db.entities

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "Data_Kambing")
data class DatakambingVo(

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,

    @ColumnInfo(name = "Nomor_Kambing")
    var nomor: String? = null,

    @ColumnInfo(name = "Nama_Kambing")
    var nama: String? = null,


) : Parcelable