package com.example.project1_group15

import retrofit2.http.GET
import retrofit2.http.Query

interface NasaApiService {

    @GET("planetary/apod")
    suspend fun getApod(@Query("api_key") apiKey: String): ApodResponse

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getMarsRoverPhotos(
        @Query("sol") sol: Int,
        @Query("api_key") apiKey: String
    ): MarsRoverPhotosResponse
}
