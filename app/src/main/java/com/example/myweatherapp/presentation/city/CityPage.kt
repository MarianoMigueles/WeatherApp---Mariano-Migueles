package com.example.myweatherapp.Presentation.City

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CityPage(
    modifier: Modifier = Modifier
) {
    val viewModel : CityViewModel = viewModel<CityViewModel>()
    CityView(
        modifier = Modifier,
        state = viewModel.state,
    ) {
        viewModel.execute(it)
    }
}