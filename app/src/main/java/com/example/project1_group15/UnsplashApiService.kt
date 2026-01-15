package com.example.project1_group15

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApiService {

    @Headers(
        "Accept-Version: v1"
    )
    @GET("search/photos")
    suspend fun searchSpacePhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int
    ): UnsplashResponse
}