package com.mkstudio.cosmicview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
class NeoWsViewModel : ViewModel() {

    private val repository = NasaRepository()

    private val _neoResult = MutableLiveData<Result<List<NeoWs>>>()
    val neoResult: LiveData<Result<List<NeoWs>>> = _neoResult

    fun fetchNeoWsData(apiKey: String) {
        viewModelScope.launch {
            try {
                val startDate = java.time.LocalDate.now().toString()
                val endDate = java.time.LocalDate.now().plusDays(7).toString()

                val result = repository.getNeoWs(apiKey, startDate, endDate)

                val mappedResult = result.map { response ->
                    response.near_earth_objects
                        .values
                        .flatten()
                }

                _neoResult.postValue(mappedResult)

            } catch (e: Exception) {
                _neoResult.postValue(Result.failure(e))
            }
        }
    }
}
