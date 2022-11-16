package com.eduside.smartgoat.data.repository.sensor

import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.smartgoat.data.local.sp.SessionLogin
import com.eduside.smartgoat.data.remote.ApiServices
import com.eduside.smartgoat.data.remote.model.Error.Companion.getErrorMessage
import com.eduside.smartgoat.data.remote.response.GetSensorResponse
import com.eduside.smartgoat.data.remote.response.PostLoginResponse
import com.eduside.smartgoat.data.remote.response.PostRegistResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SensorRepository @Inject constructor(
    private val apiServices: ApiServices,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val regSensorResponse = MutableLiveData<GetSensorResponse>()
//    email,ttl,lokasiKandang,domisili,nodeid,name,password,password_confirmation
    suspend fun getSensor(): GetSensorResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val getResponse = apiServices.getSensor()
                if (getResponse.isSuccessful) {
                    getResponse.body()?.let {
                        regSensorResponse.postValue(it)
                    }
                } else {
                    error.postValue(
                        getResponse.errorBody()?.let { getErrorMessage(it.string()) })
                }
                loading.postValue(false)
            } catch (e: Exception) {
                loading.postValue(false)
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
            return@withContext GetSensorResult(error, loading, regSensorResponse)
        }
    }

}