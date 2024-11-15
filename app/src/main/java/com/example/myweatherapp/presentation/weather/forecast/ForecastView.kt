package com.example.myweatherapp.presentation.weather.forecast

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.myweatherapp.icons.IconManager
import com.example.myweatherapp.icons.models.IconModel
import com.example.myweatherapp.icons.models.IconSize
import com.example.myweatherapp.presentation.GetIcon
import com.example.myweatherapp.presentation.events.EmptyView
import com.example.myweatherapp.presentation.events.ErrorView
import com.example.myweatherapp.presentation.events.LoadingView
import com.example.myweatherapp.repository.models.ListForecast
import com.example.ui.theme.displayFontFamily

@Composable
fun ForecastView(
    state: ForecastState,
    execute: (ForecastIntention) -> Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        execute(ForecastIntention.Update)
    }
    when(state) {
        is ForecastState.Loading -> LoadingView()
        is ForecastState.Error ->  ErrorView(state.message)
        is ForecastState.Success -> SuccessfulView(state.forecast)
        is ForecastState.Empty -> EmptyView()
    }
}

@Composable
fun SuccessfulView(forecast: List<ListForecast>) {
    Column (
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .fillMaxWidth()
            .border(
                BorderStroke(0.dp, Color.Black),
                shape = RoundedCornerShape(8.dp)
            )
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.3f))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        forecast.forEach { item ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val days = 7
                for (i in 1..days) {
                    DayWeatherInfo("x", item.main.temp_max.toString(), item.main.temp_min.toString(), IconManager.windIcon)
                }
            }
            Spacer(Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val boxes = 3
                for (i in 1..boxes) {
                    WeatherStats("x", IconManager.windIcon)
                }
            }
        }
    }
}

@Composable
private fun DayWeatherInfo(day: String, max: String, min: String, icon: IconModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextDislay(day, MaterialTheme.typography.titleLarge.fontSize)
        Spacer(Modifier.height(5.dp))
        GetIcon(icon, IconSize.small)
        Spacer(Modifier.height(5.dp))
        TextDislay(max, MaterialTheme.typography.bodyLarge.fontSize)
        TextDislay(min, MaterialTheme.typography.bodyLarge.fontSize)
    }
}

//humidity: String, precipitation: String, windSpeed: String
@Composable
private fun WeatherStats(value: String, icon: IconModel) {
    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            GetIcon(icon, IconSize.small)
            TextDislay(value, MaterialTheme.typography.bodyLarge.fontSize)
        }
    }
}

@Composable
private fun TextDislay(text: String, size: TextUnit, modifier: Modifier? = null) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontFamily = displayFontFamily,
        fontSize = size,
        modifier = modifier ?: Modifier
    )
}




