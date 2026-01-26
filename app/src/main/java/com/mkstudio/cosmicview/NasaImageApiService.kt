package com.mkstudio.cosmicview

import retrofit2.http.GET
import retrofit2.http.Query

interface NasaImageApiService {

    @GET("search")
    suspend fun searchImages(
        @Query("q") query: String,
        @Query("media_type") mediaType: String = "image"
    ): NasaImageSearchResponse
}
