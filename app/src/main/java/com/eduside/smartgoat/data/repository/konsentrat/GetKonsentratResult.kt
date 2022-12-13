package com.eduside.smartgoat.data.repository.konsentrat

import androidx.lifecycle.LiveData
import com.eduside.smartgoat.data.remote.response.*

data class GetKonsentratResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val reqGetKonsentrat: LiveData<GetKonsentratResponse>
)