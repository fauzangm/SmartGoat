package com.eduside.bappenda.di

import android.content.Context
import androidx.room.Room
import com.eduside.smartgoat.data.local.db.AppDB
import com.eduside.smartgoat.data.local.db.dao.DataKambingDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DbModules {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDB {
        return Room
            .databaseBuilder(context, AppDB::class.java, "SmartGoat.db")
            .fallbackToDestructiveMigration()
            .build()
    }
//
    @Singleton
    @Provides
    fun provideDataKambing(db: AppDB): DataKambingDao {
        return db.datakambingDao()
    }
//
//    @Singleton
//    @Provides
//    fun provideDocLainnyaDao(db: AppDB): DocLainnyaDao {
//        return db.docLainnyaDao()
//    }



//    @Provides
//    @Singleton
//    fun provideAppliactionScope() = CoroutineScope(SupervisorJob())
}