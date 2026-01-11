package com.example.project1_group15


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val repository = NasaRepository()
    private val apiKey = "gfWcMDTnSlpSMxy2Qog4KccxZenNuyT5m9i42pdj"

    private val _apodState = MutableLiveData<Result<ApodResponse>>()
    val apodState: LiveData<Result<ApodResponse>> = _apodState

    fun loadApod() {
        viewModelScope.launch {
            try {
                val result = repository.getApod(apiKey)
                _apodState.postValue(result)
            } catch (e: Exception) {
                _apodState.postValue(Result.failure(e))
            }
        }
    }

}