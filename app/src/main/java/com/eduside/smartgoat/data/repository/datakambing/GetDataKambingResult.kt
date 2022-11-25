package com.eduside.smartgoat.data.repository.datakambing

import androidx.lifecycle.LiveData
import com.eduside.smartgoat.data.remote.response.GetDataKambingResponse
import com.eduside.smartgoat.data.remote.response.PostLoginResponse
import com.eduside.smartgoat.data.remote.response.PostRegistResponse
import com.eduside.smartgoat.data.remote.response.PutTimbanganResponse

data class GetDataKambingResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val reqGetKambing: LiveData<GetDataKambingResponse>
)