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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.compose.AppTheme
import com.example.myweatherapp.icons.IconManager
import com.example.myweatherapp.icons.getWeatherIcon
import com.example.myweatherapp.icons.models.IconModel
import com.example.myweatherapp.icons.models.IconSize
import com.example.myweatherapp.presentation.DisplayIcon
import com.example.myweatherapp.presentation.events.EmptyView
import com.example.myweatherapp.presentation.events.ErrorView
import com.example.myweatherapp.presentation.events.LoadingView
import com.example.myweatherapp.repository.models.Clouds
import com.example.myweatherapp.repository.models.Coord
import com.example.myweatherapp.repository.models.Main
import com.example.myweatherapp.repository.models.Weather
import com.example.myweatherapp.repository.models.WeatherDTO
import com.example.myweatherapp.repository.models.Wind
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
    Column( modifier = Modifier.padding(horizontal = 20.dp)) {
        CreateTempBox(currentWeather.main.temp.toInt().toString(), currentWeather.main.feels_like.toInt().toString())
        WeatherInfoRow(currentWeather.name, currentWeather.weather[0].main, currentWeather.weather[0].icon)
    }
    WeatherStats(
        currentWeather.main.humidity.toString(),
        currentWeather.rain.rain.toString(),
        currentWeather.wind.speed.toString()
    )
    Spacer(Modifier.height(20.dp))
}

@Composable
private fun CreateTempBox(temperature: String, fellLike: String) {
    Column {
        TextDislay(
            temperature+"º",
            MaterialTheme.typography.displayLarge.fontSize,
        )
        TextDislay(
            "Sensación térmica: $fellLike º",
            MaterialTheme.typography.bodySmall.fontSize,
            Modifier.offset(y = (-25).dp)
        )
    }
}

@Composable
private fun WeatherInfoRow(cityName: String, weatherDescription: String, icon: String) {
    val modifier: Modifier = Modifier
    Row{
        Spacer(modifier.weight(0.5f))
        Column (
            modifier.weight(2f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DisplayIcon(getWeatherIcon(icon), IconSize.extraLarge)
            Spacer(modifier.height(50.dp))
            TextDislay(cityName, MaterialTheme.typography.headlineMedium.fontSize, overflow = true)
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
fun WeatherStats(humidity: String, wind: String, precipitations: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                shape = RoundedCornerShape(8.dp)
            )
            .background(MaterialTheme.colorScheme.onBackground)
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        WeatherStatsDisplay("$humidity %", IconManager.humidityIcon)
        WeatherStatsDisplay("$precipitations mm", IconManager.thermometerIcon)
        WeatherStatsDisplay("$wind m/s", IconManager.windIcon)
    }
}

@Composable
private fun WeatherStatsDisplay(value: String, icon: IconModel) {
    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            DisplayIcon(icon, IconSize.small, MaterialTheme.colorScheme.primary)
            TextDislay(value, MaterialTheme.typography.bodyMedium.fontSize)
        }
    }
}

@Composable
private fun VerticalText(text:String) {
    Column {
        text.forEach { char ->
            TextDislay(
                char.toString(),
                MaterialTheme.typography.titleLarge.fontSize,
                Modifier.align(Alignment.CenterHorizontally).offset(y = (-50).dp)
            )
        }
    }
}

@Composable
private fun TextDislay(text: String, size: TextUnit, modifier: Modifier? = null, overflow: Boolean = false) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        fontFamily = displayFontFamily,
        fontSize = size,
        modifier = modifier ?: Modifier,
        maxLines = 1,
        overflow = if (overflow) { TextOverflow.Ellipsis } else {
            TextOverflow.Visible
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SuccessfulViewPreview() {
    AppTheme {
        val weather = WeatherDTO(
            base = "stations",
            name = "Buenos Aires",
            coord = Coord(lon = -58.4438, lat = -34.6118),
            weather = listOf(
                Weather(id = 800, main = "Clear", description = "Despejado", icon = "01d")
            ),
            main = Main(
                temp = 25.0,
                feels_like = 27.0,
                temp_min = 22.0,
                temp_max = 28.0,
                pressure = 1012,
                humidity = 60
            ),
            wind = Wind(speed = 5.0, deg = 180),
            clouds = Clouds(all = 0)
        )

        SuccessfulView(weather)
    }
}