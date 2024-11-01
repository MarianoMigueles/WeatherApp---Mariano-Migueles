package com.example.myweatherapp.Presentation.Weather

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WeatherPage(
    modifier: Modifier = Modifier
) {
    val viewModel : WeatherViewModel = viewModel<WeatherViewModel>()
    WeatherView(
        modifier = Modifier,
        state = viewModel.state,
    ) {
        viewModel.execute(it)
    }
}