package com.example.project1_group15

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UnsplashViewModel : ViewModel() {

    private val repository = UnsplashRepository()

    private val _heroImageUrl = MutableLiveData<String>()
    val heroImageUrl: LiveData<String> = _heroImageUrl

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun loadHeroImage() {
        viewModelScope.launch {
            try {
                val photos = repository.getSpacePhotos()
                if (photos.isNotEmpty()) {
                    _heroImageUrl.postValue(photos.random().urls.regular)
                } else {
                    _errorMessage.postValue("No space images available")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.postValue("Failed to load space image")
            }
        }
    }
}
