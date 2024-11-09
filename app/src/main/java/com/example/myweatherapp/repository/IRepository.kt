package com.example.myweatherapp.repository

import com.example.myweatherapp.repository.models.City
import com.example.myweatherapp.repository.models.Forecast
import com.example.myweatherapp.repository.models.Weather

interface IRepository {
    suspend fun getCity(city: String): List<City>
    suspend fun getWeather(lat: Float, lon: Float): Weather
    suspend fun getForecast(name: String): List<Forecast>
}