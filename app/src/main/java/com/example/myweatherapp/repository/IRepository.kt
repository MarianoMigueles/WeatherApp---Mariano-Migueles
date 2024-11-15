package com.example.myweatherapp.repository

import com.example.myweatherapp.repository.models.City
import com.example.myweatherapp.repository.models.ListForecast
import com.example.myweatherapp.repository.models.WeatherDTO

interface IRepository {
    suspend fun getCity(city: String): List<City>
    suspend fun getWeather(lat: Float, lon: Float): WeatherDTO
    suspend fun getForecast(name: String): List<ListForecast>
}