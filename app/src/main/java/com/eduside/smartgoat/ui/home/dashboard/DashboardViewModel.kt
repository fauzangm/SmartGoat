package com.eduside.smartgoat.ui.home.dashboard

import androidx.lifecycle.*
import com.eduside.smartgoat.data.repository.Fan.FanRepository
import com.eduside.smartgoat.data.repository.Fan.PutFanResult
import com.eduside.smartgoat.data.repository.Lampu.PutDimmerResult
import com.eduside.smartgoat.data.repository.Lampu.PutLampuResult
import com.eduside.smartgoat.data.repository.Lampu.SwLampuRepository
import com.eduside.smartgoat.data.repository.Timbangan.PutTImbanganResult
import com.eduside.smartgoat.data.repository.Timbangan.TimbanganRepository
import com.eduside.smartgoat.data.repository.sensor.GetSensorResult
import com.eduside.smartgoat.data.repository.sensor.SensorRepository
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val timbanganRepository: TimbanganRepository,
    private val fanRepository: FanRepository,
    private val swLampuRepository: SwLampuRepository
) : ViewModel()  {

    private val putRegResult = MutableLiveData<PutTImbanganResult>()
    val putRegError = Transformations.switchMap(putRegResult) { it.error }
    val putRegLoading = Transformations.switchMap(putRegResult) { it.loading }
    val putRegResponse = Transformations.switchMap(putRegResult) { it.reqPutTimbangan }
    fun putTimbangna(requestBody: JsonObject) {
        viewModelScope.launch {
            putRegResult.postValue(timbanganRepository.putTImbangan(requestBody))
        }
    }

    private val putFanRegResult = MutableLiveData<PutFanResult>()
    val putFanRegError = Transformations.switchMap(putFanRegResult) { it.error }
    val putFanRegLoading = Transformations.switchMap(putFanRegResult) { it.loading }
    val putFanRegResponse = Transformations.switchMap(putFanRegResult) { it.reqPutFan }
    fun putFan(requestBody: JsonObject) {
        viewModelScope.launch {
            putFanRegResult.postValue(fanRepository.putFan(requestBody))
        }
    }

    private val putSwLampuRegResult = MutableLiveData<PutLampuResult>()
    val putSwLampuRegError = Transformations.switchMap(putSwLampuRegResult) { it.error }
    val putSwLampuRegLoading = Transformations.switchMap(putSwLampuRegResult) { it.loading }
    val putSwLampuRegResponse = Transformations.switchMap(putSwLampuRegResult) { it.reqPutLampu }
    fun putSwLampu(requestBody: JsonObject) {
        viewModelScope.launch {
            putSwLampuRegResult.postValue(swLampuRepository.putSwLampu(requestBody))
        }
    }

    private val putDimmerLampuRegResult = MutableLiveData<PutDimmerResult>()
    val putDimmerLampuRegError = Transformations.switchMap(putDimmerLampuRegResult) { it.error }
    val putDimmerLampuRegLoading = Transformations.switchMap(putDimmerLampuRegResult) { it.loading }
    val putDimmerLampuRegResponse = Transformations.switchMap(putDimmerLampuRegResult) { it.reqPutDimmer }
    fun putDimmerLampu(requestBody: JsonObject) {
        viewModelScope.launch {
            putDimmerLampuRegResult.postValue(swLampuRepository.putDimmerLampu(requestBody))
        }
    }
}