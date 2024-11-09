package com.example.myweatherapp.presentation.weather

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myweatherapp.repository.Repository
import com.example.myweatherapp.router.Router

@Composable
fun WeatherPage(
    navHostController: NavHostController,
    lat: Float,
    lon: Float,
    name: String
) {
    val viewModel : WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(
            repository = Repository(),
            router = Router(navHostController),
            lat = lat,
            lon = lon,
            name = name
        )
    )

    WeatherView(
        state = viewModel.state,
        execute = { intention ->
            viewModel.execute(intention)
        }
    )
}