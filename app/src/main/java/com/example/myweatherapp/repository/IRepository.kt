package com.example.myweatherapp.repository

import com.example.myweatherapp.presentation.city.City
import com.example.myweatherapp.repository.models.Forecast

interface IRepository {
    suspend fun getCity(city: String): List<City>
    suspend fun getWeather(lat: Float, lon: Float): City
    suspend fun getForecast(name: String): List<Forecast>
}