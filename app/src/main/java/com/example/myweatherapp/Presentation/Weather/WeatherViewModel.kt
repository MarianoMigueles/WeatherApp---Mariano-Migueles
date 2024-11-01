package com.example.myweatherapp.Presentation.Weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WeatherViewModel() : ViewModel() {
    var state by mutableStateOf<WeatherState>(WeatherState.Empty)

    fun execute(intention: WeatherIntention) {
        when(intention) {
            is WeatherIntention.Update -> loadCity()
        }
    }

    private fun loadCity() {
        state = WeatherState.Loading
        viewModelScope.launch {

        }
        state = WeatherState.Error("No funciono el servidor, esta todo roto")
    }
}