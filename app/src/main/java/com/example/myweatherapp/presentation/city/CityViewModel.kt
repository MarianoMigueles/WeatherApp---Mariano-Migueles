package com.example.myweatherapp.presentation.city

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.repository.IRepository
import com.example.myweatherapp.repository.Repository
import com.example.myweatherapp.repository.models.City
import com.example.myweatherapp.router.NavTarget
import com.example.myweatherapp.router.Router
import kotlinx.coroutines.launch

class CityViewModel(
    val repository: IRepository,
    private val navigator: Router
) : ViewModel() {
    var state by mutableStateOf<CityState>(CityState.Empty)
    var cities: List<City> = emptyList()

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
                cities = repository.getCity(cityName)
                if(cities.isEmpty()) {
                    state = CityState.Empty
                } else {
                    state = CityState.Success(cities)
                }
            } catch (exception: Exception) {
                state = CityState.Error(exception.localizedMessage ?: "Unknown error")
            }
        }
    }

    private  fun selectCity(city: City) {
        val route = NavTarget.Weather(city.lat, city.lon, city.name)
        navigator.navigate(route)
    }
}

class CityViewModelFactory(
    private val repository: Repository,
    private val navigator: Router
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return CityViewModel(repository, navigator) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}