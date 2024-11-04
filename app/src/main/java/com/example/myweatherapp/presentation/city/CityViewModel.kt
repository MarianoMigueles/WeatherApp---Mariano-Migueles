package com.example.myweatherapp.presentation.city

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.repository.models.City
import kotlinx.coroutines.launch

class CityViewModel() : ViewModel() {
    var state by mutableStateOf<CityState>(CityState.Success(city = City("Marisport", -34.6105f, -58.3922f, "Argentina", "Buenos Aires")))

    fun execute(intention: CityIntention) {
        when(intention) {
            is CityIntention.Update -> loadCity()
        }
    }

    private fun loadCity() {
        state = CityState.Loading
        viewModelScope.launch {

        }
        state = CityState.Error("No funciono el servidor, esta todo roto")
    }
}