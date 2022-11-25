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
    ],
    version = 3,
    exportSchema = false
)
@TypeConverters(ConvertersBitmap::class)
abstract class AppDB : RoomDatabase() {

    abstract fun datakambingDao(): DataKambingDao

}