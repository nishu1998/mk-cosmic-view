package com.mkstudio.cosmicview

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class NasaRepository {

    private val apiService: NasaApiService

    init {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(NasaApiService::class.java)
    }

    suspend fun getApod(apiKey: String): Result<ApodResponse> {
        return try {
            Result.success(apiService.getApod(apiKey))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun getNeoWs(apiKey: String, startDate: String, endDate: String): Result<NeoWsResponse> {
        return try {
            Result.success(apiService.getNeoWs(startDate, endDate, apiKey))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
