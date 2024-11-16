package com.example.myweatherapp.presentation.weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myweatherapp.presentation.PageView
import com.example.myweatherapp.presentation.weather.actual.WeatherState
import com.example.myweatherapp.presentation.weather.actual.WeatherView
import com.example.myweatherapp.presentation.weather.actual.WeatherViewModel
import com.example.myweatherapp.presentation.weather.actual.WeatherViewModelFactory
import com.example.myweatherapp.presentation.weather.forecast.ForecastView
import com.example.myweatherapp.presentation.weather.forecast.ForecastViewModel
import com.example.myweatherapp.presentation.weather.forecast.ForecastViewModelFactory
import com.example.myweatherapp.repository.IRepository
import com.example.myweatherapp.repository.Repository
import com.example.myweatherapp.repository.models.WeatherDTO
import com.example.myweatherapp.router.Router

@Composable
fun WeatherPage(
    navHostController: NavHostController,
    lat: Float,
    lon: Float,
    name: String
) {
    val weatherViewModel : WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(
            repository = Repository(),
            router = Router(navHostController),
            lat = lat,
            lon = lon,
            name = name
        )
    )

    val forecastViewModel : ForecastViewModel = viewModel(
        factory = ForecastViewModelFactory(
            repository = Repository(),
            router = Router(navHostController),
            name = name
        )
    )

    PageView<WeatherDTO>(
        model = when (val state = weatherViewModel.state) {
            is WeatherState.Success -> state.weatherDTO
            else -> WeatherDTO()
        }
    ) {
        WeatherView(
            state = weatherViewModel.state,
            execute = { intention ->
                weatherViewModel.execute(intention)
            }
        )
        ForecastView(
            state = forecastViewModel.state,
            execute = { intention ->
                forecastViewModel.execute(intention)
            }
        )
    }

}