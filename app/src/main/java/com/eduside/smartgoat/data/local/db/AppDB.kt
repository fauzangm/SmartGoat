package com.eduside.smartgoat.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eduside.smartgoat.data.local.db.dao.DataKambingDao
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import com.eduside.smartgoat.util.ConvertersBitmap

@Database(
    entities =
    [
        DatakambingVo::class,
//        DocLainnyaVo::class,
//        EspHiburanlVo::class,
//        EspHotelVo::class,
//        EspParkirVo::class,
//        FotoObjekVo::class,
//        JenisUsahalVo::class,
//        KecamatanVo::class,
//        KelurahanVo::class,
//        ListEsptpdVo::class,
//        NpwpdVo::class,
//        ProvinsiVo::class,
//        SkpdVo::class,
//        SuratIzinVo::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(ConvertersBitmap::class)
abstract class AppDB : RoomDatabase() {

    abstract fun datakambingDao(): DataKambingDao
//    abstract fun docLainnyaDao(): DocLainnyaDao
//    abstract fun espHiburanDao(): EspHiburanDao
//    abstract fun espHotelDao(): EspHotelDao
//    abstract fun espParkirDao(): EspParkirDao
//    abstract fun fotoObjekDao(): FotoObjekDao
//    abstract fun jenisUsahaDao():JenisUsahaDao
//    abstract fun kecamatanDao(): KecamatanDao
//    abstract fun kelurahanDao(): KelurahanDao
//    abstract fun listEsptpdDao(): ListEsptpdDao
//    abstract fun npwpdDao(): NpwpdDao
//    abstract fun provinsiDao(): ProvinsiDao
//    abstract fun skpdDao(): SkpdDao
//    abstract fun suratIzinDao(): SuratIzinDao
}