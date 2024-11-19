package com.example.myweatherapp.presentation.city

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.repository.IRepository
import com.example.myweatherapp.repository.models.City
import com.example.myweatherapp.router.IRouter
import com.example.myweatherapp.router.NavTarget
import kotlinx.coroutines.launch

class CityViewModel(
    val repository: IRepository,
    private val navigator: IRouter
) : ViewModel() {
    var state by mutableStateOf<CityState>(CityState.Empty)
    private var cities: List<City> = emptyList()

    fun execute(intention: CityIntention) {
        when(intention) {
            is CityIntention.Search -> searchCity(intention.cityName)
            is CityIntention.Select -> selectCity(intention.city)
        }
    }

    private fun searchCity(cityName: String) {
        state = CityState.Loading
        viewModelScope.launch {
            try {
                cities = repository.getCity(cityName) ?: emptyList()
                state = if(cities.isEmpty()) {
                    CityState.Empty
                } else {
                    CityState.Success(cities)
                }
            } catch (exception: Exception) {
                state = CityState.Error(exception.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun selectCity(city: City) {
        val route = NavTarget.Weather(city.lat, city.lon, city.name)
        navigator.navigate(route)
    }
}

class CityViewModelFactory(
    private val repository: IRepository,
    private val navigator: IRouter
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return CityViewModel(repository, navigator) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}