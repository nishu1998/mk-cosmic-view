package com.example.project1_group15

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UnsplashRepository {

    private val api: UnsplashApiService by lazy {

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader(
                        "Authorization",
                        "Client-ID ${BuildConfig.UNSPLASH_KEY}"
                    )
                    .addHeader("Accept-Version", "v1")
                    .addHeader(
                        "User-Agent",
                        "MKCosmicView/1.0 (Android)"
                    )
                    .build()
                chain.proceed(request)
            }
            .build()

        Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashApiService::class.java)
    }


    suspend fun getSpacePhotos(): List<UnsplashPhoto> {
        try {
            val response = api.searchSpacePhotos(
                query = "space",
                perPage = 30
            )

            android.util.Log.d(
                "UNSPLASH_API",
                "Photos received: ${response.results.size}"
            )

            return response.results

        } catch (e: Exception) {
            android.util.Log.e("UNSPLASH_API", "API FAILED", e)
            throw e
        }
    }
}
