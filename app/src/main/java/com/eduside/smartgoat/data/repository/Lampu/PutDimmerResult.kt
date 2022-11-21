package com.eduside.smartgoat.data.repository.Lampu

import androidx.lifecycle.LiveData
import com.eduside.smartgoat.data.remote.response.PostLoginResponse
import com.eduside.smartgoat.data.remote.response.PostRegistResponse
import com.eduside.smartgoat.data.remote.response.PutDImmerResponse
import com.eduside.smartgoat.data.remote.response.PutTimbanganResponse

data class PutDimmerResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val reqPutDimmer: LiveData<PutDImmerResponse>
)