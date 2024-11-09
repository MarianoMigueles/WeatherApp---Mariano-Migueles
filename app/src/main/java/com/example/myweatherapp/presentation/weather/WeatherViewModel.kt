package com.example.myweatherapp.presentation.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.repository.IRepository
import com.example.myweatherapp.router.Router
import kotlinx.coroutines.launch

class WeatherViewModel(
    val repository: IRepository,
    val router: Router,
    val lat: Float,
    val lon : Float,
    val name: String
) : ViewModel() {
    var state by mutableStateOf<WeatherState>(WeatherState.Empty)

    fun execute(intention: WeatherIntention) {
        when(intention) {
            is WeatherIntention.Update -> loadWeather()
        }
    }

    private fun loadWeather() {
        state = WeatherState.Loading

        viewModelScope.launch {
            try {
                val weather = repository.getWeather(lat = lat, lon = lon)
                state = WeatherState.Success(weather)
            } catch (exception: Exception) {
                state = WeatherState.Error(exception.localizedMessage ?: "Unknown error")
            }
        }
    }
}

class WeatherViewModelFactory(
    private val repository: IRepository,
    private val router: Router,
    private val lat: Float,
    private val lon: Float,
    private val name: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override  fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository, router, lat, lon, name) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}