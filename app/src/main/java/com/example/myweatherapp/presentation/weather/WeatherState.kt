package com.example.myweatherapp.presentation.weather

import com.example.myweatherapp.repository.models.Weather

sealed class WeatherState {
    data object Empty : WeatherState()
    data object Loading : WeatherState()
    data class Success(val weather: Weather) : WeatherState()
    data class Error(val message: String) : WeatherState()
}
