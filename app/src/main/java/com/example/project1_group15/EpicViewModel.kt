package com.example.project1_group15

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EpicViewModel : ViewModel() {
    private val repository = NasaRepository()
    private val _epicImages = MutableLiveData<List<EpicImageResponse>>()
    val epicImages: LiveData<List<EpicImageResponse>> get() = _epicImages
    private val apiKey = "gfWcMDTnSlpSMxy2Qog4KccxZenNuyT5m9i42pdj"

    fun fetchEpicImages() {
        viewModelScope.launch {
            try {
                val images = repository.getEpicImages(apiKey)
                _epicImages.postValue(images)
            } catch (e: Exception) {
                Log.e("EpicViewModel", "Error fetching EPIC images", e)
            }
        }
    }

    fun fetchEpicImagesByDate(date: String) {
        viewModelScope.launch {
            try {
                val images = repository.getEpicImagesByDate(apiKey, date)
                _epicImages.postValue(images)
            } catch (e: Exception) {
                Log.e("EpicViewModel", "Error fetching EPIC images by date", e)
            }
        }
    }
}
