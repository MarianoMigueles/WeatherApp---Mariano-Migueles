package com.example.myweatherapp.presentation.weather.actual

import com.example.myweatherapp.repository.models.WeatherDTO

sealed class WeatherState {
    data object Empty : WeatherState()
    data object Loading : WeatherState()
    data class Success(val weatherDTO: WeatherDTO) : WeatherState()
    data class Error(val message: String) : WeatherState()
}
