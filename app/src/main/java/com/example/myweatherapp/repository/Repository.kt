package com.example.myweatherapp.repository


import android.util.Log
import com.example.myweatherapp.repository.models.City
import com.example.myweatherapp.repository.models.ForecastDTO
import com.example.myweatherapp.repository.models.ListForecast
import com.example.myweatherapp.repository.models.WeatherDTO

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Repository : IRepository {

    private object Api {
        const val APIKEY = "afa304be5e5e3a942353c511a821be9e"
        const val CITYCALL = "https://api.openweathermap.org/geo/1.0/direct"
        const val WEATHERCALL = "https://api.openweathermap.org/data/2.5/weather"
        const val FORECASTCALL = "https://api.openweathermap.org/data/2.5/forecast"
    }

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun getCity(city: String): List<City> {
        val response = client.get(Api.CITYCALL) {
            parameter("q", city)
            parameter("limit", 10)
            parameter("appid", Api.APIKEY)
        }

        validateHttpStatus(response.status)

        return response.body<List<City>>()
    }

    override suspend fun getWeather(lat: Float, lon: Float): WeatherDTO {
        val response = client.get(Api.WEATHERCALL) {
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("units","metric")
            parameter("appid", Api.APIKEY)
        }

        validateHttpStatus(response.status)

        return response.body<WeatherDTO>()
    }

    override suspend fun getForecast(name: String): List<ListForecast> {
        val response = client.get(Api.FORECASTCALL) {
            parameter("q",name)
            parameter("units","metric")
            parameter("appid",Api.APIKEY)
        }

        validateHttpStatus(response.status)

        Log.d("for", "${response.body<ForecastDTO>().list}")

        return response.body<ForecastDTO>().list.take(7)
    }

    private fun validateHttpStatus(status: HttpStatusCode): Unit {
        if(status != HttpStatusCode.OK) {
            throw Exception()
        }
    }

}