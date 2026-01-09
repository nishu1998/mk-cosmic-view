package com.example.project1_group15

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MarsRoverPhotosViewModel : ViewModel() {
    private val repository = NasaRepository()

    private val _marsResult = MutableLiveData<Result<MarsRoverPhotosResponse>>()
    val marsResult: LiveData<Result<MarsRoverPhotosResponse>> = _marsResult

    private val apiKey = "gfWcMDTnSlpSMxy2Qog4KccxZenNuyT5m9i42pdj"


    fun loadMarsRoverPhotos(sol: Int = 1000) {
        viewModelScope.launch {
            val result = repository.getMarsRoverPhotos(
                apiKey = apiKey,
                sol = sol
            )
            _marsResult.postValue(result)
        }
    }
}