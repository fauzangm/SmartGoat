package com.eduside.smartgoat.ui.auth.login

import androidx.lifecycle.*
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import com.eduside.smartgoat.data.repository.datakambing.DataKambingRepository
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataKambingRepository: DataKambingRepository
) : ViewModel() {
    fun addDataKambing(datakambingVo: DatakambingVo) {
        viewModelScope.launch {
            dataKambingRepository.addDataKambing(datakambingVo)
        }
    }

}