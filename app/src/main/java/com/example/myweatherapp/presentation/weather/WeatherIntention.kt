package com.example.myweatherapp.presentation.weather

sealed class WeatherIntention {
    data object Update: WeatherIntention()
}