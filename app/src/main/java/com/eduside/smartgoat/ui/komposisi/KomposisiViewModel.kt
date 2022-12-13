package com.eduside.smartgoat.ui.komposisi

import androidx.lifecycle.*
import com.eduside.smartgoat.data.repository.Auth.AuthRepository
import com.eduside.smartgoat.data.repository.Auth.PostRegResult
import com.eduside.smartgoat.data.repository.datakambing.DataKambingRepository
import com.eduside.smartgoat.data.repository.datakambing.GetDataKambingResult
import com.eduside.smartgoat.data.repository.konsentrat.GetHijauanResult
import com.eduside.smartgoat.data.repository.konsentrat.GetKonsentratResult
import com.eduside.smartgoat.data.repository.konsentrat.KonsentratRepository
import com.eduside.smartgoat.data.repository.sensor.GetSensorResult
import com.eduside.smartgoat.data.repository.sensor.SensorRepository
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KomposisiViewModel @Inject constructor(
    private val konsentratRepository: KonsentratRepository
) : ViewModel()  {

    private val getKonsentratResult = MutableLiveData<GetKonsentratResult>()
    val getKonsentratError = Transformations.switchMap(getKonsentratResult) { it.error }
    val getKonsentratLoading = Transformations.switchMap(getKonsentratResult) { it.loading }
    val getKonsentratResponse = Transformations.switchMap(getKonsentratResult) { it.reqGetKonsentrat }
    fun getDataKambing(id:String) {
        viewModelScope.launch {
            getKonsentratResult.postValue(konsentratRepository.getKonsentrat(id))
        }
    }


    private val getHijauanResult = MutableLiveData<GetHijauanResult>()
    val getHijauanError = Transformations.switchMap(getHijauanResult) { it.error }
    val getHijauanLoading = Transformations.switchMap(getHijauanResult) { it.loading }
    val getHijauanResponse = Transformations.switchMap(getHijauanResult) { it.reqGetHijauan }
    fun getHijauan(id:String) {
        viewModelScope.launch {
            getHijauanResult.postValue(konsentratRepository.getHijauan(id))
        }
    }

}
