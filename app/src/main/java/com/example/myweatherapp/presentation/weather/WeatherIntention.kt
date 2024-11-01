package com.example.myweatherapp.Presentation.Weather

sealed class WeatherIntention {
    data object Update: WeatherIntention()
}