package com.eduside.smartgoat.data.repository.berita

import androidx.lifecycle.LiveData
import com.eduside.smartgoat.data.remote.response.GetDataBeritaResponse
import com.eduside.smartgoat.data.remote.response.PostLoginResponse
import com.eduside.smartgoat.data.remote.response.PostRegistResponse
import com.eduside.smartgoat.data.remote.response.PutTimbanganResponse

data class GetBeritaResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val reqGetBerita: LiveData<GetDataBeritaResponse>
)