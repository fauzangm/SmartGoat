package com.eduside.smartgoat.ui.home.notifications

import androidx.lifecycle.*
import com.eduside.smartgoat.data.repository.berita.BeritaRepository
import com.eduside.smartgoat.data.repository.berita.GetBeritaResult
import com.eduside.smartgoat.data.repository.datakambing.DataKambingRepository
import com.eduside.smartgoat.data.repository.datakambing.GetDataKambingResult
import com.eduside.smartgoat.data.repository.sensor.GetSensorResult
import com.eduside.smartgoat.data.repository.sensor.SensorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel @Inject constructor(
    private val beritaRepository: BeritaRepository
) : ViewModel()  {

    private val getRegResult = MutableLiveData<GetBeritaResult>()
    val getRegError = Transformations.switchMap(getRegResult) { it.error }
    val getRegLoading = Transformations.switchMap(getRegResult) { it.loading }
    val getRegResponse = Transformations.switchMap(getRegResult) { it.reqGetBerita }
    fun getBerita() {
        viewModelScope.launch {
            getRegResult.postValue(beritaRepository.getBerita())
        }
    }
}