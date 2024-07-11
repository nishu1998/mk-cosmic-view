package com.example.project1_group15

data class EpicImageResponse(
    val identifier: String,
    val caption: String,
    val image: String,
    val date: String,
    val centroid_coordinates: Coordinates
)

data class Coordinates(
    val lat: Double,
    val lon: Double
)
