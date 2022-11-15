package com.eduside.smartgoat.data.repository.Auth

import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.smartgoat.data.local.sp.SessionLogin
import com.eduside.smartgoat.data.remote.ApiServices
import com.eduside.smartgoat.data.remote.model.Error.Companion.getErrorMessage
import com.eduside.smartgoat.data.remote.response.PostLoginResponse
import com.eduside.smartgoat.data.remote.response.PostRegistResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val sessionManager: SessionLogin,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val regWPResponse = MutableLiveData<PostRegistResponse>()
//    email,ttl,lokasiKandang,domisili,nodeid,name,password,password_confirmation
    suspend fun postRegistrasiWP(
    requestBody: JsonObject
//        email: String,
//        ttl: String,
//        lokasiKandang: String,
//        domisili: String,
//        nodeid: Int,
//        name: String,
//        password: String,
//        password_confirmation: String
    ): PostRegResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val postRegistrasiResponse = apiServices.postRegist(requestBody)
                if (postRegistrasiResponse.isSuccessful) {
                    postRegistrasiResponse.body()?.let {
                        regWPResponse.postValue(it)
                    }
                } else {
                    error.postValue(
                        postRegistrasiResponse.errorBody()?.let { getErrorMessage(it.string()) })
                }
                loading.postValue(false)
            } catch (e: Exception) {
                loading.postValue(false)
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
            return@withContext PostRegResult(error, loading, regWPResponse)
        }
    }

    private val logResponse = MutableLiveData<PostLoginResponse>()
    suspend fun postLogin(requestBody: JsonObject): PostLoginResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val postLoginWPResponse = apiServices.postLogin(requestBody)
                if (postLoginWPResponse.isSuccessful) {
                    postLoginWPResponse.body()?.let {
                        sessionManager.put(SessionLogin.TOKEN, it.message?.token.toString())
                        sessionManager.put(SessionLogin.PREF_IS_LOGIN, true)
                        logResponse.postValue(it)
                    }
                } else {
                    error.postValue(
                        postLoginWPResponse.errorBody()?.let { getErrorMessage(it.string()) })
                }
                loading.postValue(false)
            } catch (e: Exception) {
                loading.postValue(false)
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
            return@withContext PostLoginResult(error, loading, logResponse)
        }
    }
}