package com.example.project1_group15

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NasaImageViewModel : ViewModel() {

    private val repository = NasaImageRepository()

    private val _heroImageUrl = MutableLiveData<String?>()
    val heroImageUrl: LiveData<String?> = _heroImageUrl

    fun loadHeroImage() {
        viewModelScope.launch {
            try {
                // 🔥 THIS is the only important line
                val imageUrl = repository.getHeroImage()
                _heroImageUrl.postValue(imageUrl)
            } catch (e: Exception) {
                _heroImageUrl.postValue(null)
            }
        }
    }
}
