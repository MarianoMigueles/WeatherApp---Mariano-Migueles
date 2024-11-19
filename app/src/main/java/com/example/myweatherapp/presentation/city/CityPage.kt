package com.example.myweatherapp.presentation.city

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myweatherapp.presentation.CreateButton
import com.example.myweatherapp.presentation.PageView
import com.example.myweatherapp.repository.Repository
import com.example.myweatherapp.router.NavTarget
import com.example.myweatherapp.router.Router

@Composable
fun CityPage(
    navigator: NavHostController
) {
    val viewModel : CityViewModel = viewModel(
        factory = CityViewModelFactory(
            repository = Repository(),
            navigator = Router(navigator)
        )
    )

    PageView{
        CityView(
            state = viewModel.state,
            execute = { intention ->
                viewModel.execute(intention)
            }
        )
    }
}