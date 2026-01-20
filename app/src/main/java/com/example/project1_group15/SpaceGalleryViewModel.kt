package com.example.project1_group15

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SpaceGalleryViewModel : ViewModel() {

    private val repository = NasaImageRepository()

    private val _images = MutableLiveData<List<String>>()
    val images: LiveData<List<String>> = _images

    fun loadGalleryImages(query: String = "space") {
        viewModelScope.launch {
            try {
                val results = repository.searchImages(query)
                _images.postValue(results)
            } catch (e: Exception) {
                _images.postValue(emptyList())
            }
        }
    }
}

