package com.example.project1_group15

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NasaImageRepository {

    private val api: NasaImageApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://images-api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NasaImageApiService::class.java)
    }

    suspend fun getHeroImage(query: String): String? {
        return try {
            val response = api.searchImages(query)

            // Get first image link safely
            val imageUrl = response.collection.items
                .firstOrNull()
                ?.links
                ?.firstOrNull()
                ?.href

            Log.d("NASA_IMAGE_API", "Image URL: $imageUrl")
            imageUrl

        } catch (e: Exception) {
            Log.e("NASA_IMAGE_API", "Failed to fetch image", e)
            null
        }
    }
}
