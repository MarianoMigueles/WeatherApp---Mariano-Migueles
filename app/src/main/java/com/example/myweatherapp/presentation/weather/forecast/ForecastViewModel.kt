package com.example.myweatherapp.presentation.weather.forecast

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myweatherapp.repository.IRepository
import com.example.myweatherapp.router.Router
import kotlinx.coroutines.launch

class ForecastViewModel(
    val repository: IRepository,
    val router: Router,
    val name: String
) : ViewModel() {
    var state by mutableStateOf<ForecastState>(ForecastState.Empty)

    fun execute(intention: ForecastIntention) {
        when(intention) {
            is ForecastIntention.Update -> loadForecast()
        }
    }

    private fun loadForecast() {
        state = ForecastState.Loading

        viewModelScope.launch {
            try {
                val forecast = repository.getForecast(name).filter { true }
                state = ForecastState.Success(forecast)
            } catch (exception: Exception) {
                state = ForecastState.Error(exception.localizedMessage ?: "Unknown error")
            }
        }
    }
}

class ForecastViewModelFactory(
    private val repository: IRepository,
    private val router: Router,
    private val name: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override  fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            return ForecastViewModel(repository, router, name) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}