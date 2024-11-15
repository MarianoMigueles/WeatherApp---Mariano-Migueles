package com.example.myweatherapp.presentation.weather.forecast

sealed class ForecastIntention {
    data object Update: ForecastIntention()
}