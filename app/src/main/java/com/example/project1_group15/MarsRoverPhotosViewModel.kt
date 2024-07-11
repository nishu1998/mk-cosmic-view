package com.example.project1_group15

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class MarsRoverPhotosViewModel : ViewModel() {
    private val repository = NasaRepository()

    val marsRoverPhotos: LiveData<List<MarsRoverPhoto>> = liveData {
        val data = repository.getMarsRoverPhotos(apiKey = "gfWcMDTnSlpSMxy2Qog4KccxZenNuyT5m9i42pdj", sol = 1000)
        emit(data.photos)
    }

    fun loadMarsRoverPhotos() {
        // This function can be left empty or used if you need to trigger loading data in specific situations
    }
}