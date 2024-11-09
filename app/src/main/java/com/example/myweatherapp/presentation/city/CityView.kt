package com.example.myweatherapp.presentation.city

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.myweatherapp.presentation.city.cityView.LoadingView
import com.example.myweatherapp.presentation.city.cityView.EmptyView
import com.example.myweatherapp.presentation.city.cityView.ErrorView

@Composable
fun CityView(
    state: CityState,
    execute: (CityIntention) -> Unit
) {
    when(state) {
        is CityState.Loading -> LoadingView()
        is CityState.Error ->  ErrorView(state.message)
        is CityState.Success -> Text("Executed") //SuccessfulView(
//            cities = state.cities,
//            onCitySelected = { city -> execute(CityIntention.Select(city)) },
//            onSearch = { cityName -> execute(CityIntention.Search(cityName)) }
//        )
        is CityState.Empty -> EmptyView()
    }
}