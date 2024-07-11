package com.example.project1_group15

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NasaRepository {

    private val apiService: NasaApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(NasaApiService::class.java)
    }

    suspend fun getApod(apiKey: String) = apiService.getApod(apiKey)

    suspend fun getMarsRoverPhotos(apiKey: String, sol: Int) = apiService.getMarsRoverPhotos(sol, apiKey)
}
