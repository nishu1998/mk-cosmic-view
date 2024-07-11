package com.example.project1_group15

data class MarsRoverPhoto(
    val id: Int,
    val img_src: String,
    val earth_date: String,
    val rover: Rover
)

data class Rover(
    val id: Int,
    val name: String
)
