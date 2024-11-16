package com.example.myweatherapp.presentation.weather.actual

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
import com.example.myweatherapp.repository.models.WeatherDTO
import com.example.ui.theme.displayFontFamily

@Composable
fun WeatherView(
    state: WeatherState,
    execute: (WeatherIntention) -> Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        execute(WeatherIntention.Update)
    }
    when(state) {
        is WeatherState.Loading -> LoadingView()
        is WeatherState.Error ->  ErrorView(state.message)
        is WeatherState.Success -> SuccessfulView(state.weatherDTO)
        is WeatherState.Empty -> EmptyView()
    }
}

@Composable
fun SuccessfulView(currentWeather: WeatherDTO) {
    val modifier: Modifier = Modifier
    Column( modifier = modifier.padding(horizontal = 20.dp)) {
        CreateTempBox(currentWeather.main.temp.toString())
        Spacer(modifier.weight(0.5f))
        WeatherInfoRow(currentWeather.name, currentWeather.weather[0].main)
    }
}

@Composable
private fun CreateTempBox(temperature: String) {
    val modifier: Modifier = Modifier
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextDislay(
            temperature,
            MaterialTheme.typography.displayLarge.fontSize
        )
        Column {
            TextDislay(
                "º",
                MaterialTheme.typography.displayMedium.fontSize
            )
            TextDislay(
                "C",
                MaterialTheme.typography.displayMedium.fontSize
            )
        }
    }
}

@Composable
private fun WeatherInfoRow(cityName: String, weatherDescription: String) {
    val modifier: Modifier = Modifier
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier.weight(0.5f))
        Column (
            modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GetIcon(IconManager.windIcon, IconSize.extraLarge)
            TextDislay(cityName, MaterialTheme.typography.headlineMedium.fontSize)
        }
        Box(
            modifier.weight(0.5f)
                .padding(end = 10.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            VerticalText(weatherDescription)
        }
    }
}

@Composable
private fun VerticalText(text:String) {
    Column {
        text.forEach { char ->
            TextDislay(
                char.toString(),
                MaterialTheme.typography.headlineLarge.fontSize,
                Modifier.align(Alignment.CenterHorizontally)
            )
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





