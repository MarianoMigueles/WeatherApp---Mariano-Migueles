package com.example.myweatherapp.repository.models

data class Weather (
    val cityName: String,
    val temperature: String,
    val description: String,
    val windChill: String
)