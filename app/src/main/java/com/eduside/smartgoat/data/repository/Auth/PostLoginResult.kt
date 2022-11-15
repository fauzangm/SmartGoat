package com.eduside.smartgoat.data.repository.Auth

import androidx.lifecycle.LiveData
import com.eduside.smartgoat.data.remote.response.PostLoginResponse
import com.eduside.smartgoat.data.remote.response.PostRegistResponse

data class PostLoginResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val regLoginResponse: LiveData<PostLoginResponse>
)