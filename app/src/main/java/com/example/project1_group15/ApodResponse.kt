package com.example.project1_group15

data class ApodResponse(
    val date: String,
    val title: String,
    val explanation: String,
    val media_type: String,
    val url: String?,
    val hdurl: String?
)
