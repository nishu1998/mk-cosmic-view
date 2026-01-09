package com.example.project1_group15

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
class NeoWsViewModel : ViewModel() {
    private val repository = NasaRepository()

    private val _neoResult = MutableLiveData<Result<List<NeoWs>>>()
    val neoResult: LiveData<Result<List<NeoWs>>> = _neoResult


    fun fetchNeoWsData(
        apiKey: String,
        startDate: String = "2023-07-01",
        endDate: String = "2023-07-07"
    ) {
        viewModelScope.launch {
            val result = repository.getNeoWs(apiKey, startDate, endDate)

            val mappedResult = result.map { response ->
                response.near_earth_objects
                    .values
                    .flatten()
            }

            _neoResult.postValue(mappedResult)
        }
    }
}