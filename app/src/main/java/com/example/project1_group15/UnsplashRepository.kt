package com.example.project1_group15

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class UnsplashRepository {
    private val api: UnsplashApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApiService::class.java)
    }
    suspend fun getSpacePhotos(): List<UnsplashPhoto> {
        return api.searchSpacePhotos(
            apiKey = BuildConfig.UNSPLASH_KEY
        ).results
    }
}