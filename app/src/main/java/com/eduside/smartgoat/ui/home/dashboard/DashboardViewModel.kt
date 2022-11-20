package com.eduside.smartgoat.ui.home.dashboard

import androidx.lifecycle.*
import com.eduside.smartgoat.data.repository.Fan.FanRepository
import com.eduside.smartgoat.data.repository.Fan.PutFanResult
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
    private val fanRepository: FanRepository
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
}