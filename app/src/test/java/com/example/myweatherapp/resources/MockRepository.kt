package com.example.myweatherapp.resources

import com.example.myweatherapp.repository.IRepository
import com.example.myweatherapp.repository.models.City
import com.example.myweatherapp.repository.models.Clouds
import com.example.myweatherapp.repository.models.Coord
import com.example.myweatherapp.repository.models.ListForecast
import com.example.myweatherapp.repository.models.Main
import com.example.myweatherapp.repository.models.Rain
import com.example.myweatherapp.repository.models.Weather
import com.example.myweatherapp.repository.models.WeatherDTO
import com.example.myweatherapp.repository.models.Wind

class MockRepository  : IRepository {

    val cordoba = City(
        name = "Cordoba",
        lat = -23.0f,
        lon = -24.3f,
        country = "Argentina",
        state = "Buenos Aires"
    )
    val bsAs = City(
        name = "Buenos Aires",
        lat = -23.0f,
        lon = -24.3f,
        country = "Argentina",
        state = "Buenos Aires"
    )
    val laPlata = City(
        name = "La Plata",
        lat = -23.0f,
        lon = -24.3f,
        country = "Argentina",
        state = "Buenos Aires"
    )

    val cities = listOf(cordoba,bsAs,laPlata)

    override suspend fun getCity(city: String): List<City> {
        if (city == "error"){
            throw Exception()
        }
        return cities.filter {  it.name.contains(city,ignoreCase = true) }
    }

    override suspend fun getWeather(lat: Float, lon: Float): WeatherDTO {
        TODO("Not yet implemented")
    }

    override suspend fun getForecast(name: String): List<ListForecast> {
        TODO("Not yet implemented")
    }
}


class RepositoryMockError : IRepository {

    override suspend fun getCity(city: String): List<City> {
        throw Exception()
    }

    override suspend fun getWeather(lat: Float, lon: Float): WeatherDTO {
        throw Exception()
    }

    override suspend fun getForecast(name: String): List<ListForecast> {
        throw Exception()
    }

}