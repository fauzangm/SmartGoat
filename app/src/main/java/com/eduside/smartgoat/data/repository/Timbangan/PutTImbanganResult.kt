package com.eduside.smartgoat.data.repository.Timbangan

import androidx.lifecycle.LiveData
import com.eduside.smartgoat.data.remote.response.PostLoginResponse
import com.eduside.smartgoat.data.remote.response.PostRegistResponse
import com.eduside.smartgoat.data.remote.response.PutTimbanganResponse

data class PutTImbanganResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val reqPutTimbangan: LiveData<PutTimbanganResponse>
)