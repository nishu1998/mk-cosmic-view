package com.example.project1_group15

import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApiService {

    @GET("search/photos")
    suspend fun searchSpacePhotos(
        @Query("query") query: String = "space astronomy",
        @Query("per_page") perPage: Int = 20,
        @Query("client_id") apiKey: String
    ): UnsplashResponse
}