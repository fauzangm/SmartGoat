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

    @ColumnInfo(name = "warna_Kambing")
    val warna: String? = null,

    @ColumnInfo(name = "saudara")
    val saudara: String? = null,

    @ColumnInfo(name = "foto_Kambing")
    val image: String? = null,

    @ColumnInfo(name = "rfid_Jantan")
    val rfidJantan: String? = null,

    @ColumnInfo(name = "kelamin_Kambing")
    val gender: String? = null,

    @ColumnInfo(name = "Ras_Kambing")
    val ras: String? = null,

    @ColumnInfo(name = "rfid_Betina_Kambing")
    val rfidBetina: String? = null,

    @ColumnInfo(name = "tanggal_lahir_Kambing")
    val ttl: String? = null,

    @ColumnInfo(name = "lokasi_Kambing")
    val lokasi: String? = null,

    @ColumnInfo(name = "bobot_sekarang")
    val bobotSekarang: String? = null,

    @ColumnInfo(name = "rfid_Kambing")
    val rfid: String? = null,

    @ColumnInfo(name = "bobot_lahir")
    val bobotLahir: String? = null

) : Parcelable