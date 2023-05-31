package com.eduside.smartgoat.data.repository.Lampu

import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.smartgoat.data.remote.ApiServices
import com.eduside.smartgoat.data.remote.model.Error.Companion.getErrorMessage
import com.eduside.smartgoat.data.remote.response.PutDImmerResponse
import com.eduside.smartgoat.data.remote.response.PutTimbanganResponse
import com.eduside.smartgoat.util.UNKNOWN_DATABASE_ERROR
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SwLampuRepository @Inject constructor(
    private val apiServices: ApiServices,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val reqResponse = MutableLiveData<PutTimbanganResponse>()
    suspend fun putSwLampu(
    requestBody: JsonObject
    ): PutLampuResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val putResponse = apiServices.putSwLampu(requestBody)
                if (putResponse.isSuccessful) {
                    putResponse.body()?.let {
                        reqResponse.postValue(it)
                    }
                } else {
                    if (putResponse.code() !=  404){
                        error.postValue(
                            putResponse.errorBody()?.let { getErrorMessage(it.string()) })
                    }else {
                        error.postValue(UNKNOWN_DATABASE_ERROR)
                    }
                }
                loading.postValue(false)
            } catch (e: Exception) {
                loading.postValue(false)
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
            return@withContext PutLampuResult(error, loading, reqResponse)
        }
    }

    private val reqDimmerResponse = MutableLiveData<PutDImmerResponse>()
    suspend fun putDimmerLampu(
        requestBody: JsonObject
    ): PutDimmerResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val putResponse = apiServices.putDimmer(requestBody)
                if (putResponse.isSuccessful) {
                    putResponse.body()?.let {
                        reqResponse.postValue(it)
                    }
                } else {
                    if (putResponse.code() !=  404){
                        error.postValue(
                            putResponse.errorBody()?.let { getErrorMessage(it.string()) })
                    }else {
                        error.postValue(UNKNOWN_DATABASE_ERROR)
                    }
                }
                loading.postValue(false)
            } catch (e: Exception) {
                loading.postValue(false)
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
            return@withContext PutDimmerResult(error, loading, reqDimmerResponse)
        }
    }

}