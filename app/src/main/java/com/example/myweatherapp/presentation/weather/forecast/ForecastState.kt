package com.example.myweatherapp.presentation.weather.forecast

import com.example.myweatherapp.repository.models.ListForecast

sealed class ForecastState {
    data object Empty : ForecastState()
    data object Loading : ForecastState()
    data class Success(val forecast: List<ListForecast>) : ForecastState()
    data class Error(val message: String) : ForecastState()
}
