package com.eduside.smartgoat.data.repository.datakambing


import androidx.lifecycle.LiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.smartgoat.data.local.db.dao.DataKambingDao
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataKambingRepository @Inject constructor(
    private val dataKambingDao: DataKambingDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
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




//
//    private val error = MutableLiveData<String>()
//    private val loading = MutableLiveData<Boolean>()
//    private val regRegistNpwpdResponse = MutableLiveData<PostRegistNPWPDResponse>()
//
//    suspend fun postRegistNpwpd(requestBody: JsonObject): PostRegistNpwpdResult {
//        return withContext(ioDispatcher) {
//            loading.postValue(true)
//            try {
//                val postRegistNpwpdResponse = apiServices.registNpwpd(requestBody)
//                if (postRegistNpwpdResponse.isSuccessful) {
//                    postRegistNpwpdResponse.body()?.let {
//                        regRegistNpwpdResponse.postValue(it)
//                    }
//                } else {
//                    error.postValue(postRegistNpwpdResponse.errorBody()?.string().toString())
//                }
//                loading.postValue(false)
//
//            } catch (e: Exception) {
//                loading.postValue(false)
//                e.printStackTrace()
//                error.postValue(e.localizedMessage)
//            }
//            return@withContext PostRegistNpwpdResult(error, loading, regRegistNpwpdResponse)
//        }
//
//    }
//
//
//    private val reqGetNpwpdResponse = MutableLiveData<GetNpwpdResponse>()
//    suspend fun getNpwpd(): GetNpwpdResult {
//        return withContext(ioDispatcher) {
//            loading.postValue(true)
//            try {
//                val listNpwpdVo = mNpwpdDao.getAllData()
//
//                val getNpwpdResponse = apiServices.getNpwpd()
//                if (getNpwpdResponse.isSuccessful) {
//                    getNpwpdResponse.body()?.let {
//                        it.data?.forEach { data ->
//                            saveGetNpwpd(it.data)
//                        }
//                        reqGetNpwpdResponse.postValue(it)
//                    }
//                } else {
//                    error.postValue(getNpwpdResponse.errorBody()?.string().toString())
//                }
//
//                loading.postValue(false)
//
//            } catch (e: Exception) {
//                loading.postValue(false)
//                e.printStackTrace()
//                error.postValue(e.localizedMessage)
//            }
//            return@withContext GetNpwpdResult(error, loading, reqGetNpwpdResponse)
//        }
//
//    }
//
//    private suspend fun saveGetNpwpd(listNpwpd: List<DataItemNpwpd>) {
//        if (listNpwpd.isNotEmpty()) {
//            val listNpwpdItem: ArrayList<NpwpdVo> = arrayListOf()
//            listNpwpd.map {
//                listNpwpdItem.add(
//                    NpwpdVo(
//                        id = it.id!!,
//                        npwpd = it.npwpd,
//                        namapemilik = it.objekPajak?.nama,
//                        rtrw = it.objekPajak?.rtRw,
//                        jenispajak = it.rekening?.jenisUsaha,
//                        alamatpajak = it.objekPajak?.alamat,
//                        nop = it.objekPajak?.nop,
//                        objek = it.rekening?.objek,
//                        namausaha = it.rekening?.nama,
//                        rekeningId = it.rekening?.id.toString(),
//                        objekPajakId = it.objekPajakId.toString(),
//                        masaPajak = it.tanggalNpwpd?.toString()
//                    )
//
//                )
//            }
//            delay(200L)
//            mNpwpdDao.addNpwpd(listNpwpdItem)
//        }
//    }
//
//
//    private val reqGetDetailNpwpdResponse = MutableLiveData<GetDetailNpwpdResponse>()
//    suspend fun getDetailNpwpd(id: String): GetDetailNpwpdResult {
//        return withContext(ioDispatcher) {
//            loading.postValue(true)
//            try {
//                val getDetailNpwpdResponse = apiServices.getDetailNpwpd(id)
//                if (getDetailNpwpdResponse.isSuccessful) {
//                    getDetailNpwpdResponse.body()?.let {
//                        reqGetDetailNpwpdResponse.postValue(it)
//                    }
//                } else {
//                    error.postValue(getDetailNpwpdResponse.errorBody()?.string().toString())
//                }
//                loading.postValue(false)
//
//            } catch (e: Exception) {
//                loading.postValue(false)
//                e.printStackTrace()
//                error.postValue(e.localizedMessage)
//            }
//            return@withContext GetDetailNpwpdResult(error, loading, reqGetDetailNpwpdResponse)
//        }
//
//    }


}