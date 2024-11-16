package com.example.myweatherapp.presentation.weather.actual

sealed class WeatherIntention {
    data object Update: WeatherIntention()
}