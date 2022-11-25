package com.eduside.smartgoat.ui.datakambing

import androidx.lifecycle.*
import com.eduside.smartgoat.data.local.db.entities.DatakambingVo
import com.eduside.smartgoat.data.repository.datakambing.DataKambingRepository
import com.eduside.smartgoat.data.repository.datakambing.GetDataKambingResult
import com.eduside.smartgoat.data.repository.sensor.GetSensorResult
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataKambingViewModel @Inject constructor(
    private val dataKambingRepository: DataKambingRepository
) : ViewModel() {
    fun getDataKambing(): LiveData<List<DatakambingVo>> = dataKambingRepository.getDatakambing()
//    {
//        viewModelScope.launch {
//            dataKambingRepository.getAllData()
//        }


    fun searchDataNamaKambing(searchQuery: String): LiveData<List<DatakambingVo>> {
        return dataKambingRepository.searchDataNamaKambing(searchQuery).asLiveData()
    }

}