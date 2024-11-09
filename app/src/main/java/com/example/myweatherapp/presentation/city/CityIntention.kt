package com.example.myweatherapp.presentation.city

import com.example.myweatherapp.repository.models.City

sealed class CityIntention {
    data class Search(val cityName: String) : CityIntention()
    data class Select(val city: City) : CityIntention()
}