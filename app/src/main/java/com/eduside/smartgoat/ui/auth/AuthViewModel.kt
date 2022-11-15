package com.eduside.smartgoat.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduside.smartgoat.data.repository.Auth.AuthRepository
import com.eduside.smartgoat.data.repository.Auth.PostRegResult
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel()  {

    private val postRegResult = MutableLiveData<PostRegResult>()
    val postRegError = Transformations.switchMap(postRegResult) { it.error }
    val postRegLoading = Transformations.switchMap(postRegResult) { it.loading }
    val postRegResponse = Transformations.switchMap(postRegResult) { it.regWPResponse }
    fun postRegistrasiWP(requestBody: JsonObject) {
        viewModelScope.launch {
            postRegResult.postValue(authRepository.postRegistrasiWP(requestBody))
        }
    }
}