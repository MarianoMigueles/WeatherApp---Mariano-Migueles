package com.example.myweatherapp.presentation.weather

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.compose.AppTheme
import com.example.myweatherapp.icons.IconManager
import com.example.myweatherapp.icons.models.IconModel
import com.example.myweatherapp.icons.models.IconSize
import com.example.myweatherapp.presentation.CreateButton
import com.example.myweatherapp.presentation.DisplayIcon
import com.example.myweatherapp.presentation.PageView
import com.example.myweatherapp.presentation.ShareManager
import com.example.myweatherapp.presentation.weather.actual.WeatherState
import com.example.myweatherapp.presentation.weather.actual.WeatherView
import com.example.myweatherapp.presentation.weather.actual.WeatherViewModel
import com.example.myweatherapp.presentation.weather.actual.WeatherViewModelFactory
import com.example.myweatherapp.presentation.weather.forecast.ForecastView
import com.example.myweatherapp.presentation.weather.forecast.ForecastViewModel
import com.example.myweatherapp.presentation.weather.forecast.ForecastViewModelFactory
import com.example.myweatherapp.repository.Repository
import com.example.myweatherapp.repository.models.WeatherDTO
import com.example.myweatherapp.router.NavTarget
import com.example.myweatherapp.router.Router

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherPage(
    navigator: NavHostController,
    lat: Float,
    lon: Float,
    name: String
) {
    val weatherViewModel : WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(
            repository = Repository(),
            router = Router(navigator),
            lat = lat,
            lon = lon,
            name = name
        )
    )

    val forecastViewModel : ForecastViewModel = viewModel(
        factory = ForecastViewModelFactory(
            repository = Repository(),
            router = Router(navigator),
            name = name
        )
    )

    PageView(
        topBarContent = {
            CreateShareButton(weatherViewModel.state)

            var isExpanded by remember { mutableStateOf(false) }
            val dismiss: () -> Unit = { isExpanded = !isExpanded }

            CreateButton(action = dismiss) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "setting button"
                )
                DropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { dismiss() }
                ) {
                    DropdownMenuItem(
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                DisplayIcon(IconManager.cityIcon, IconSize.extraSmall, MaterialTheme.colorScheme.primary)
                                Spacer(Modifier.width(10.dp))
                                Text("Ciudad")
                            }
                        },
                        onClick = {
                            dismiss()
                            val route = NavTarget.Cities.id
                            navigator.navigate(route)
                        }
                    )
                }
            }
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

@Composable
private fun CreateShareButton(state: WeatherState) {
    val weatherDTO = when (state) {
        is WeatherState.Success -> state.weatherDTO
        else -> WeatherDTO()
    }

    val context = LocalContext.current
    val shareManager = ShareManager(context)
    CreateButton(action = { shareManager.shareWeatherStats(weatherDTO) }) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "Share button"
        )
    }
}
