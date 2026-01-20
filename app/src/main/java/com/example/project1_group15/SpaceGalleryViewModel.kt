package com.example.project1_group15

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SpaceGalleryViewModel : ViewModel() {

    private val repository = NasaImageRepository()

    private val _images = MutableLiveData<List<SpaceImage>>()
    val images: LiveData<List<SpaceImage>> = _images

    fun loadGalleryImages(query: String = "space") {
        viewModelScope.launch {
            try {
                val urls = repository.searchImages(query)

                // 🔁 Map String URLs → SpaceImage model
                val mappedImages = urls.map { url ->
                    SpaceImage(
                        imageUrl = url,
                        title = query.replaceFirstChar { it.uppercase() }
                    )
                }

                _images.postValue(mappedImages)
            } catch (e: Exception) {
                _images.postValue(emptyList())
            }
        }
    }
}
