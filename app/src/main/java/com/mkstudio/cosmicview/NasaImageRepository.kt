package com.mkstudio.cosmicview

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

    suspend fun searchImages(query: String): List<String> {
        val response = api.searchImages(query)

        return response.collection.items
            .mapNotNull { it.links?.firstOrNull()?.href }
            .shuffled()
    }

    // 🌌 HOME HERO IMAGE (single image)
    suspend fun getHeroImage(): String? {
        return try {
            val queries = listOf(
                "nebula",
                "galaxy",
                "star cluster",
            )

            val randomQuery = queries.random()
            val response = api.searchImages(randomQuery)

            response.collection.items
                .randomOrNull()
                ?.links
                ?.firstOrNull()
                ?.href
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
