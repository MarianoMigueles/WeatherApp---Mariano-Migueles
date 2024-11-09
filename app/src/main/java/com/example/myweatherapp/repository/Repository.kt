package com.example.myweatherapp.repository

import com.example.myweatherapp.repository.models.City
import com.example.myweatherapp.repository.models.Forecast
import com.example.myweatherapp.repository.models.Weather
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
        val ApiKey = "afa304be5e5e3a942353c511a821be9e"
        val CityCall = "http://api.openweathermap.org/geo/1.0/direct"
        val WeatherCall = "https://api.openweathermap.org/data/2.5/weather"
        val ForecastCall = "https://api.openweathermap.org/data/2.5/forecast"
    }

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun getCity(ciudad: String): List<City> {
        val response = client.get(Api.CityCall) {
            parameter("q", ciudad)
            parameter("limit", 10)
            parameter("appid", Api.ApiKey)
        }

        validateHttpStatus(response.status)

        return response.body<List<City>>()
    }

    override suspend fun getWeather(lat: Float, lon: Float): Weather {
        val response = client.get(Api.WeatherCall) {
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("units","metric")
            parameter("appid", Api.ApiKey)
        }

        validateHttpStatus(response.status)

        return response.body<Weather>()
    }

    override suspend fun getForecast(name: String): List<Forecast> {
        val response = client.get(Api.ForecastCall) {
            parameter("q",name)
            parameter("units","metric")
            parameter("appid",Api.ApiKey)
        }

        validateHttpStatus(response.status)

        return response.body<List<Forecast>>()
    }


    private fun validateHttpStatus(status: HttpStatusCode): Unit {
        if(status != HttpStatusCode.OK) {
            throw Exception()
        }
    }

}