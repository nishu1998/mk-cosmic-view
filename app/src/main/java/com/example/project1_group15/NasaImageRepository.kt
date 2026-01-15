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

    // 🌌 HOME HERO IMAGE (single image)
    suspend fun getHeroImage(query: String): String? {
        return try {
            val response = api.searchImages(query)
            val imageUrl = response.collection.items
                .firstOrNull()
                ?.links
                ?.firstOrNull()
                ?.href

            Log.d("NASA_IMAGE_API", "Hero Image URL: $imageUrl")
            imageUrl
        } catch (e: Exception) {
            Log.e("NASA_IMAGE_API", "Failed to fetch hero image", e)
            null
        }
    }

    // 🖼️ SPACE GALLERY (multiple images)
    suspend fun fetchGalleryImages(query: String = "space"): List<String> {
        return try {
            val response = api.searchImages(query)
            response.collection.items
                .mapNotNull { it.links?.firstOrNull()?.href }
        } catch (e: Exception) {
            Log.e("NASA_IMAGE_API", "Failed to fetch gallery images", e)
            emptyList()
        }
    }
}
