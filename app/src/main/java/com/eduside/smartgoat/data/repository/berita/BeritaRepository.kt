package com.eduside.smartgoat.data.repository.berita


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.smartgoat.data.local.db.dao.DataKambingDao
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import com.eduside.smartgoat.data.remote.ApiServices
import com.eduside.smartgoat.data.remote.response.DataKambingItem
import com.eduside.smartgoat.data.remote.response.GetDataBeritaResponse
import com.eduside.smartgoat.data.remote.response.GetDataKambingResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BeritaRepository @Inject constructor(
    private val apiServices: ApiServices,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val reqGetBeritaResponse = MutableLiveData<GetDataBeritaResponse>()
    suspend fun getBerita(): GetBeritaResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val getResponse = apiServices.getBerita()
                if (getResponse.isSuccessful) {
                    getResponse.body()?.let {
                        reqGetBeritaResponse.postValue(it)
                    }
                } else {
                    error.postValue(getResponse.errorBody()?.string().toString())
                }

                loading.postValue(false)

            } catch (e: Exception) {
                loading.postValue(false)
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
            return@withContext GetBeritaResult(error, loading, reqGetBeritaResponse)
        }

    }



}