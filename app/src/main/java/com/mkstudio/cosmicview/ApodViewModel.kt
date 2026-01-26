package com.mkstudio.cosmicview

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class ApodViewModel : ViewModel() {
    private val repository = NasaRepository()

    private val _apodResult = MutableLiveData<Result<ApodResponse>>()
    val apodResult: LiveData<Result<ApodResponse>> = _apodResult
    fun loadApod() {
        viewModelScope.launch {
            val result = repository.getApod(
                apiKey = "gfWcMDTnSlpSMxy2Qog4KccxZenNuyT5m9i42pdj"
            )
            _apodResult.postValue(result)
        }
    }
}
