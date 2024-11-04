package com.example.myweatherapp.presentation.weather

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myweatherapp.presentation.weather.weatherView.EmptyView
import com.example.myweatherapp.presentation.weather.weatherView.ErrorView
import com.example.myweatherapp.presentation.weather.weatherView.LoadingView
import com.example.myweatherapp.presentation.weather.weatherView.SuccessfulView

@Composable
fun WeatherView(
    modifier: Modifier = Modifier,
    state: WeatherState,
    execute: (WeatherIntention) -> Unit
) {
    when(state) {
        is WeatherState.Loading -> LoadingView()
        is WeatherState.Error ->  ErrorView(state.message)
        is WeatherState.Success -> SuccessfulView()
        is WeatherState.Empty -> EmptyView()
    }
}







