package com.eduside.smartgoat.data.repository.sensor

import androidx.lifecycle.LiveData
import com.eduside.smartgoat.data.remote.response.GetSensorResponse
import com.eduside.smartgoat.data.remote.response.PostRegistResponse

data class GetSensorResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val regSensorResponse: LiveData<GetSensorResponse>
)