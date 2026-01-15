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
                    val url = photos.random().urls.regular
                    android.util.Log.d("UNSPLASH_VM", "Loading image: $url")
                    _heroImageUrl.postValue(url)
                } else {
                    android.util.Log.e("UNSPLASH_VM", "Empty photo list")
                    _errorMessage.postValue("No images returned")
                }

            } catch (e: Exception) {
                android.util.Log.e("UNSPLASH_VM", "Failed to load image", e)
                _errorMessage.postValue("Failed to load space image")
            }
        }
    }
}
