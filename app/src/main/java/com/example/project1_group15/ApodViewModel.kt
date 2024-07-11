package com.example.project1_group15

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class ApodViewModel : ViewModel() {
    private val repository = NasaRepository()

    val apodData: LiveData<ApodResponse> = liveData {
        val data = repository.getApod(apiKey = "gfWcMDTnSlpSMxy2Qog4KccxZenNuyT5m9i42pdj")
        emit(data)
    }

    fun loadApod() {
        // This function can be left empty or used if you need to trigger loading data in specific situations
    }
}
