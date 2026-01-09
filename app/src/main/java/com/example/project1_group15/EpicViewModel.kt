package com.example.project1_group15

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EpicViewModel : ViewModel() {

    private val repository = NasaRepository()
    private val _epicResult = MutableLiveData<Result<List<EpicImageResponse>>>()
    val epicResult: LiveData<Result<List<EpicImageResponse>>> = _epicResult

    private val apiKey = "gfWcMDTnSlpSMxy2Qog4KccxZenNuyT5m9i42pdj"

    fun fetchEpicImages() {
        viewModelScope.launch {
            val result = repository.getEpicImages(apiKey)
            _epicResult.postValue(result)
        }
    }

    fun fetchEpicImagesByDate(date: String) {
        viewModelScope.launch {
            val result = repository.getEpicImagesByDate(apiKey, date)
            _epicResult.postValue(result)
        }
    }

}
