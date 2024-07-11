package com.example.project1_group15

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NasaApiService {

    @GET("planetary/apod")
    suspend fun getApod(@Query("api_key") apiKey: String): ApodResponse

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getMarsRoverPhotos(
        @Query("sol") sol: Int,
        @Query("api_key") apiKey: String
    ): MarsRoverPhotosResponse

    @GET("neo/rest/v1/feed")
    suspend fun getNeoWs(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String
    ): NeoWsResponse

    @GET("EPIC/api/natural/images")
    suspend fun getEpicImages(@Query("api_key") apiKey: String): List<EpicImageResponse>

    @GET("EPIC/api/natural/date/{date}")
    suspend fun getEpicImagesByDate(
        @Path("date") date: String,
        @Query("api_key") apiKey: String
    ): List<EpicImageResponse>
}
