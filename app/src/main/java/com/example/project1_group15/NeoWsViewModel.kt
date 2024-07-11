package com.example.project1_group15

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NeoWsViewModel : ViewModel() {
    val neoWsList = MutableLiveData<List<NeoWs>>()

    fun fetchNeoWsData(apiKey: String) {
        viewModelScope.launch {
            try {
                val response = NasaRepository().getNeoWs(apiKey, "2023-07-01", "2023-07-07")
                val flattenedList = response.near_earth_objects.flatMap { it.value }
                neoWsList.postValue(flattenedList)
            } catch (e: Exception) {
                // Handle the exception
                e.printStackTrace()
            }
        }
    }
}
