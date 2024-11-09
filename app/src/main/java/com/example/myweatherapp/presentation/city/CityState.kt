package com.example.myweatherapp.presentation.city
import com.example.myweatherapp.repository.models.City

sealed class CityState {
    data object Empty : CityState()
    data object Loading : CityState()
    data class Success(val cities: List<City>) : CityState()
    data class Error(val message: String) : CityState()
}
