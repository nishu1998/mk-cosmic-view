package com.example.project1_group15

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NasaApiService {

    @GET("planetary/apod")
    suspend fun getApod(@Query("api_key") apiKey: String): ApodResponse


    @GET("neo/rest/v1/feed")
    suspend fun getNeoWs(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): NeoWsResponse

}
