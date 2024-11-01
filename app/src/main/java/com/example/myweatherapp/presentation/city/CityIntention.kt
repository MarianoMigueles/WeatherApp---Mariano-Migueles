package com.example.myweatherapp.Presentation.City

sealed class CityIntention {
    data object Update: CityIntention()
}