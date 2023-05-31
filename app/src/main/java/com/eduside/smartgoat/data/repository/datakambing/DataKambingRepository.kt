package com.eduside.smartgoat.data.repository.datakambing


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.smartgoat.data.local.db.dao.DataKambingDao
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import com.eduside.smartgoat.data.remote.ApiServices
import com.eduside.smartgoat.data.remote.response.DataKambingItem
import com.eduside.smartgoat.data.remote.response.GetDataKambingResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataKambingRepository @Inject constructor(
    private val dataKambingDao: DataKambingDao,
    private val apiServices: ApiServices,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    //    fun getNpwpd(): LiveData<List<NpwpdVo>> = mNpwpdDao.getNpwpd()
    fun getDatakambing(): LiveData<List<DatakambingVo>> = dataKambingDao.getKambing()

    suspend fun getAllData() {
        withContext(ioDispatcher) {
            dataKambingDao.getAllData()
        }
    }

    //implement coroutine
    suspend fun addDataKambing(dataKambingvo: DatakambingVo) {
        withContext(ioDispatcher) {
            dataKambingDao.addDatakambing(dataKambingvo)
        }
    }

    //search no
    fun searchDataNoKambing(searchQuery: String): Flow<List<DatakambingVo>> {
        return dataKambingDao.searchDataNoKambing(searchQuery)
    }

    //search nama
    fun searchDataNamaKambing(searchQuery: String): Flow<List<DatakambingVo>> {
        return dataKambingDao.searchDataNamaKambing(searchQuery)
    }

    suspend fun clear() {
        withContext(ioDispatcher) {
            dataKambingDao.clear()
        }
    }

    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val reqGetKambingResponse = MutableLiveData<GetDataKambingResponse>()
    suspend fun getDataKambing(): GetDataKambingResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val listKambingVo = dataKambingDao.getAllData()
                val getResponse = apiServices.getDataKambing()
                if (getResponse.isSuccessful) {
                    getResponse.body()?.let {
                        it.data?.forEach { data ->
                            saveGetNpwpd(it.data)
                        }
                        Log.e("datakambing",it.data.toString())
                        reqGetKambingResponse.postValue(it)
                    }
                } else {
                    Log.e("code",getResponse.code().toString())
                    if (getResponse.code() !=  404){
                        error.postValue(getResponse.errorBody()?.string().toString())
                    }
                }

                loading.postValue(false)

            } catch (e: Exception) {
                loading.postValue(false)
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
            return@withContext GetDataKambingResult(error, loading, reqGetKambingResponse)
        }

    }

    private suspend fun saveGetNpwpd(listData: List<DataKambingItem>) {
        if (listData.isNotEmpty()) {
            val kambingVoItem: ArrayList<DatakambingVo> = arrayListOf()
            listData.map {
                kambingVoItem.add(
                    DatakambingVo(
                        id = it.id!!,
                        warna = it.warna,
                        saudara = it.saudara,
                        image = it.image,
                        rfidJantan = it.rfid_jantan,
                        gender = it.gender,
                        ras = it.ras,
                        rfidBetina = it.rfid_betina,
                        ttl = it.ttl,
                        lokasi = it.lokasi,
                        bobotSekarang = it.bobot_sekarang,
                        rfid = it.rfid,
                        bobotLahir = it.bobot_lahir
                    )

                )
            }
            delay(200L)
            dataKambingDao.addDatakambing(kambingVoItem)
        }
    }


}