package com.eduside.smartgoat.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import kotlinx.coroutines.flow.Flow

@Dao
interface DataKambingDao {

    @Query("DELETE FROM data_kambing")
    suspend fun clear()

    //ditambahkan suspen dikarenakan menggunaka coroutine
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDatakambing(datakambingVo: DatakambingVo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDatakambing(datakambingVo: List<DatakambingVo>)

    //Search
    @Query("SELECT * FROM data_kambing WHERE id LIKE :searchQuery")
    fun searchDataNoKambing(searchQuery: String): Flow<List<DatakambingVo>>

    //Search
    @Query("SELECT * FROM data_kambing WHERE Ras_Kambing LIKE :searchQuery OR id LIKE :searchQuery ")
    fun searchDataNamaKambing(searchQuery: String): Flow<List<DatakambingVo>>


    @Query("SELECT * FROM data_kambing")
    suspend fun getAllData() : List<DatakambingVo>

    //cara kita membaca datanya yang berupa list
    @Query("SELECT * FROM data_kambing")
    fun getKambing(): LiveData<List<DatakambingVo>>

}