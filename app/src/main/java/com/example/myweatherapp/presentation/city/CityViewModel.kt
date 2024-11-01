package com.example.myweatherapp.presentation.city

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CityViewModel() : ViewModel() {
    var state by mutableStateOf<CityState>(CityState.Success(city = City()))

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