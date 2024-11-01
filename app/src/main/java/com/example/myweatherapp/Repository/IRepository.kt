package com.example.myweatherapp.Repository

import com.example.myweatherapp.Presentation.City.City
import com.example.myweatherapp.Repository.Models.Forecast

interface IRepository {
    suspend fun getCity(city: String): List<City>
    suspend fun getWeather(lat: Float, lon: Float): City
    suspend fun getForecast(name: String): List<Forecast>
}