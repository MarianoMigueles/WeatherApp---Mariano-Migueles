package com.example.myweatherapp.presentation.city

sealed class CityIntention {
    data object Update: CityIntention()
}