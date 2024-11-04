package com.example.myweatherapp.presentation.city

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myweatherapp.presentation.city.cityView.LoadingView
import com.example.myweatherapp.presentation.city.cityView.EmptyView
import com.example.myweatherapp.presentation.city.cityView.ErrorView
import com.example.myweatherapp.presentation.city.cityView.SuccessfulView

@Composable
fun CityView(
    modifier: Modifier = Modifier,
    state: CityState,
    execute: (CityIntention) -> Unit
) {
    when(state) {
        is CityState.Loading -> LoadingView()
        is CityState.Error ->  ErrorView(state.message)
        is CityState.Success -> SuccessfulView()
        is CityState.Empty -> EmptyView()
    }
}

