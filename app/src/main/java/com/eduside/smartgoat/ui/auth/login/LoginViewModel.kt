package com.eduside.smartgoat.ui.auth.login

import androidx.lifecycle.*
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import com.eduside.smartgoat.data.repository.Auth.AuthRepository
import com.eduside.smartgoat.data.repository.Auth.PostLoginResult
import com.eduside.smartgoat.data.repository.Auth.PostRegResult
import com.eduside.smartgoat.data.repository.datakambing.DataKambingRepository
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataKambingRepository: DataKambingRepository,
    private val authRepository: AuthRepository
) : ViewModel() {
    fun addDataKambing(datakambingVo: DatakambingVo) {
        viewModelScope.launch {
            dataKambingRepository.addDataKambing(datakambingVo)
        }
    }

    private val postLogResult = MutableLiveData<PostLoginResult>()
    val postLogError = Transformations.switchMap(postLogResult) { it.error }
    val postLogLoading = Transformations.switchMap(postLogResult) { it.loading }
    val postLogResponse = Transformations.switchMap(postLogResult) { it.regLoginResponse }
    fun postLogin(requestBody: JsonObject) {
        viewModelScope.launch {
            postLogResult.postValue(authRepository.postLogin(requestBody))
        }
    }

}