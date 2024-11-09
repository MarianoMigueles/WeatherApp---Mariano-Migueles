package com.example.myweatherapp.presentation.weather.weatherView

import android.health.connect.datatypes.units.Temperature
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.myweatherapp.icons.IconManager
import com.example.myweatherapp.icons.models.IconModel
import com.example.myweatherapp.icons.models.IconSize
import com.example.myweatherapp.presentation.GetIcon
import com.example.myweatherapp.presentation.PageView
import com.example.myweatherapp.repository.models.Weather
import com.example.ui.theme.displayFontFamily

@Composable
fun SuccessfulView(currentWeather: Weather) {

    PageView { modifier ->
        Column( modifier = modifier.padding(horizontal = 20.dp)) {
            CreateTempBox(currentWeather.temperature)
            Spacer(modifier.weight(0.5f))
            WeatherInfoRow(currentWeather.cityName, currentWeather.description)
            Spacer(modifier.weight(0.5f))
            WeatherDetailsColumn(currentWeather)
            Spacer(modifier.weight(1f))
        }
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
                MaterialTheme.typography.headlineMedium.fontSize,
                Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun WeatherDetailsColumn(weather: Weather) {
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            val days = 7
            for (i in 1..days) {
                DayWeatherInfo("x", "x", "x", IconManager.windIcon)
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

//@Preview(showBackground = true)
//@Composable
//fun SuccessfulViewPreview() {
//    AppTheme {
//        SuccessfulView()
//    }
//}