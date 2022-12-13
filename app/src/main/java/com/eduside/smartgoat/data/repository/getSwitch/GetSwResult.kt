package com.eduside.smartgoat.data.repository.getSwitch

import androidx.lifecycle.LiveData
import com.eduside.smartgoat.data.remote.response.*

data class GetSwResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val reqGetSw: LiveData<GetSwitchResponse>
)