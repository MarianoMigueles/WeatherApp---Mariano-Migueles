package com.example.myweatherapp.Ciudad

sealed class CityIntention {
    data object Update: CityIntention()
}