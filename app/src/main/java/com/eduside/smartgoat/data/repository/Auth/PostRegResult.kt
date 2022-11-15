package com.eduside.smartgoat.data.repository.Auth

import androidx.lifecycle.LiveData
import com.eduside.smartgoat.data.remote.response.PostRegistResponse

data class PostRegResult (
    val error: LiveData<String>,
    val loading: LiveData<Boolean>,
    val regWPResponse: LiveData<PostRegistResponse>
)