package com.eduside.smartgoat.data.repository.konsentrat


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.smartgoat.data.local.db.dao.DataKambingDao
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import com.eduside.smartgoat.data.remote.ApiServices
import com.eduside.smartgoat.data.remote.response.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KonsentratRepository @Inject constructor(
    private val apiServices: ApiServices,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val regGetKonsentrat = MutableLiveData<GetKonsentratResponse>()
    suspend fun getKonsentrat(id:String): GetKonsentratResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val getResponse = apiServices.getKonsentrat(id)
                if (getResponse.isSuccessful) {
                    getResponse.body()?.let {
                        regGetKonsentrat.postValue(it)
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
            return@withContext GetKonsentratResult(error, loading, regGetKonsentrat)
        }

    }


    private val regGetHijauan = MutableLiveData<GetHijauanResponse>()
    suspend fun getHijauan(id:String): GetHijauanResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val getResponse = apiServices.getHijauan(id)
                if (getResponse.isSuccessful) {
                    getResponse.body()?.let {
                        regGetHijauan.postValue(it)
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
            return@withContext GetHijauanResult(error, loading, regGetHijauan)
        }

    }



}