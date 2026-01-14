package com.example.project1_group15

// Top-level response from Unsplash search API
data class UnsplashResponse(
    val results: List<UnsplashPhoto>
)

// Represents a single photo item
data class UnsplashPhoto(
    val id: String,
    val description: String?,
    val urls: UnsplashUrls
)

// Holds different image sizes
data class UnsplashUrls(
    val regular: String,
    val full: String
)
