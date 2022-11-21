package com.eduside.smartgoat.data.repository.image

import androidx.lifecycle.LiveData
import com.eduside.smartgoat.data.remote.response.GetImageResponse
import com.eduside.smartgoat.data.remote.response.PostLoginResponse
import com.eduside.smartgoat.data.remote.response.PostRegistResponse
import com.eduside.smartgoat.data.remote.response.PutTimbanganResponse

data class GetImageResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val reqGetImage: LiveData<GetImageResponse>
)