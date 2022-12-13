package com.eduside.smartgoat.data.repository.konsentrat

import androidx.lifecycle.LiveData
import com.eduside.smartgoat.data.remote.response.*

data class GetHijauanResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val reqGetHijauan: LiveData<GetHijauanResponse>
)