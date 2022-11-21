package com.eduside.smartgoat.ui.cctv

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduside.smartgoat.data.repository.Auth.AuthRepository
import com.eduside.smartgoat.data.repository.Auth.PostRegResult
import com.eduside.smartgoat.data.repository.image.GetImageResult
import com.eduside.smartgoat.data.repository.image.ImageRepository
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel()  {

    private val GetRegResult = MutableLiveData<GetImageResult>()
    val GetRegError = Transformations.switchMap(GetRegResult) { it.error }
    val GetRegLoading = Transformations.switchMap(GetRegResult) { it.loading }
    val GetRegResponse = Transformations.switchMap(GetRegResult) { it.reqGetImage }
    fun getImage() {
        viewModelScope.launch {
            GetRegResult.postValue(imageRepository.GetImage())
        }
    }
}