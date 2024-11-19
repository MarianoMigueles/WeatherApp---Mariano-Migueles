package com.example.myweatherapp.icons

import androidx.compose.material3.MaterialTheme
import com.example.myweatherapp.R
import com.example.myweatherapp.icons.models.IconModel

object IconManager {
    val errorIcon = IconModel(
        name = "error",
        imageVector = R.drawable.error,
        description = "Icono de error"
    )

    val loadingIcon = IconModel(
        name = "loading",
        imageVector = R.drawable.loading,
        description = "Icono de carga"
    )

    val emptyIcon = IconModel(
        name = "empty",
        imageVector = R.drawable.wind,
        description = "Icono de vacio"
    )

    val windIcon = IconModel(
        name = "Wind",
        imageVector = R.drawable.wind,
        description = "Icono de vieto"
    )

    val thermometerIcon = IconModel(
        name = "Wind",
        imageVector = R.drawable.thermometer,
        description = "Icono de termometro"
    )

    val humidityIcon = IconModel(
        name = "Wind",
        imageVector = R.drawable.humidity,
        description = "Icono de humedad"
    )

    val cityIcon = IconModel(
        name = "City",
        imageVector = R.drawable.city,
        description = "Icono de ciudad"
    )

    val themeIcon = IconModel(
        name = "Theme",
        imageVector = R.drawable.app_theme,
        description = "Icono de tema"
    )
}

fun getWeatherIcon(iconCode: String): IconModel {
    val iconName = when (iconCode) {
        "01d", "01n" -> "Clear"
        "02d", "02n", "03d", "03n", "04d", "04n" -> "Clouds"
        "09d", "09n", "10d", "10n" -> "Rain"
        "11d", "11n" -> "Thunderstorm"
        "13d", "13n" -> "Snow"
        "50d", "50n" -> "Atmosphere"
        else -> iconCode
    }

    val iconResource = weatherIconMap[iconName] ?: R.drawable.error

    return IconModel(
        name = iconName,
        imageVector = iconResource,
        description = "Icono de $iconName"
    )
}

val weatherIconMap = mapOf(
    "Thunderstorm" to R.drawable.electric_storm,
    "Drizzle" to R.drawable.rain_storm,
    "Rain" to R.drawable.rain_storm,
    "Snow" to R.drawable.snow,
    "Atmosphere" to R.drawable.mist,
    "Clear" to R.drawable.clear,
    "Clouds" to R.drawable.cloudy
)