package com.example.myweatherapp.presentation.weather

sealed class WeatherState {
    data object Empty : WeatherState()
    data object Loading : WeatherState()
    data class Success(val weather: Weather) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

class Weather() {
    var name: String = "LLoviedo"
}