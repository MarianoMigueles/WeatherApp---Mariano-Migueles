package com.example.myweatherapp.Presentation.City

sealed class CityState {
    data object Empty : CityState()
    data object Loading : CityState()
    data class Success(val city: City) : CityState()
    data class Error(val message: String) : CityState()
}

class City() {
    var name: String = "Sao Pablo"
}