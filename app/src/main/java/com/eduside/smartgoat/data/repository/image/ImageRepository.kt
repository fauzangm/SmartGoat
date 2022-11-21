package com.eduside.smartgoat.data.repository.image

import androidx.lifecycle.MutableLiveData
import com.eduside.bappenda.di.IoDispatcher
import com.eduside.smartgoat.data.remote.ApiServices
import com.eduside.smartgoat.data.remote.model.Error.Companion.getErrorMessage
import com.eduside.smartgoat.data.remote.response.GetImageResponse
import com.eduside.smartgoat.data.remote.response.PutTimbanganResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val apiServices: ApiServices,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    private val error = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private val reqResponse = MutableLiveData<GetImageResponse>()
    suspend fun GetImage(): GetImageResult {
        return withContext(ioDispatcher) {
            loading.postValue(true)
            try {
                val GetResponse = apiServices.getAllImage()
                if (GetResponse.isSuccessful) {
                    GetResponse.body()?.let {
                        reqResponse.postValue(it)
                    }
                } else {
                    error.postValue(
                        GetResponse.errorBody()?.let { getErrorMessage(it.string()) })
                }
                loading.postValue(false)
            } catch (e: Exception) {
                loading.postValue(false)
                e.printStackTrace()
                error.postValue(e.localizedMessage)
            }
            return@withContext GetImageResult(error, loading, reqResponse)
        }
    }

}