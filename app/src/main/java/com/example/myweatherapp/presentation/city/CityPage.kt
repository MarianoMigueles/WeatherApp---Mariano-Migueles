package com.example.myweatherapp.presentation.city

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myweatherapp.presentation.PageView
import com.example.myweatherapp.repository.Repository
import com.example.myweatherapp.repository.models.City
import com.example.myweatherapp.router.Router

@Composable
fun CityPage(
    navHostController: NavHostController
) {
    val viewModel : CityViewModel = viewModel(
        factory = CityViewModelFactory(
            repository = Repository(),
            navigator = Router(navHostController)
        )
    )

    PageView<City> {
        CityView(
            state = viewModel.state,
            execute = { intention ->
                viewModel.execute(intention)
            }
        )
    }
}