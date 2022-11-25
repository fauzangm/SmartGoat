package com.eduside.smartgoat.ui.home.home

import androidx.lifecycle.*
import com.eduside.smartgoat.data.repository.Auth.AuthRepository
import com.eduside.smartgoat.data.repository.Auth.PostRegResult
import com.eduside.smartgoat.data.repository.datakambing.DataKambingRepository
import com.eduside.smartgoat.data.repository.datakambing.GetDataKambingResult
import com.eduside.smartgoat.data.repository.sensor.GetSensorResult
import com.eduside.smartgoat.data.repository.sensor.SensorRepository
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sensorRepository: SensorRepository,
    private val dataKambingRepository: DataKambingRepository
) : ViewModel()  {

    private val getRegResult = MutableLiveData<GetSensorResult>()
    val getRegError = Transformations.switchMap(getRegResult) { it.error }
    val getRegLoading = Transformations.switchMap(getRegResult) { it.loading }
    val getRegResponse = Transformations.switchMap(getRegResult) { it.regSensorResponse }
    fun getSensor() {
        viewModelScope.launch {
            getRegResult.postValue(sensorRepository.getSensor())
        }
    }



    private val getKambingRegResult = MutableLiveData<GetDataKambingResult>()
    val getKambingRegError = Transformations.switchMap(getKambingRegResult) { it.error }
    val getKambingRegLoading = Transformations.switchMap(getKambingRegResult) { it.loading }
    val getKambingRegResponse = Transformations.switchMap(getKambingRegResult) { it.reqGetKambing }
    fun getDataKambing() {
        viewModelScope.launch {
            getKambingRegResult.postValue(dataKambingRepository.getDataKambing())
        }
    }
}