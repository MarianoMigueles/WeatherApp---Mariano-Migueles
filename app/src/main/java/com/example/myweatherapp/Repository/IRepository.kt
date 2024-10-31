package com.example.myweatherapp.Repository

import com.example.myweatherapp.Ciudad.Ciudad
import com.example.myweatherapp.Repository.Models.Forecast

interface IRepository {
    suspend fun getCity(city: String): List<Ciudad>
    suspend fun getWeather(lat: Float, lon: Float): Ciudad
    suspend fun getForecast(name: String): List<Forecast>
}