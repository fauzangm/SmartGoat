package com.eduside.smartgoat.data.repository.Fan

import androidx.lifecycle.LiveData
import com.eduside.smartgoat.data.remote.response.PostLoginResponse
import com.eduside.smartgoat.data.remote.response.PostRegistResponse
import com.eduside.smartgoat.data.remote.response.PutTimbanganResponse

data class PutFanResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val reqPutFan: LiveData<PutTimbanganResponse>
)