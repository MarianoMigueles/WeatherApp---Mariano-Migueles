package com.example.myweatherapp.presentation.weather.forecast

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.DayOfWeek
import java.util.Locale
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.myweatherapp.icons.IconManager
import com.example.myweatherapp.icons.getWeatherIcon
import com.example.myweatherapp.icons.models.IconModel
import com.example.myweatherapp.icons.models.IconSize
import com.example.myweatherapp.presentation.DisplayIcon
import com.example.myweatherapp.presentation.events.EmptyView
import com.example.myweatherapp.presentation.events.ErrorView
import com.example.myweatherapp.presentation.events.LoadingView
import com.example.myweatherapp.repository.models.ListForecast
import com.example.ui.theme.displayFontFamily

@RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SuccessfulView(forecast: List<ListForecast>) {
    Column (
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
                shape = RoundedCornerShape(8.dp)
            )
            .background(MaterialTheme.colorScheme.onBackground)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            forecast.forEach { item ->
                DayWeatherInfo(getDayOfWeek(item.dt_txt), item.main.temp_max.toInt().toString(),
                    item.main.temp_min.toInt().toString(), item.weather[0].icon)
            }
        }
    }
}

@Composable
private fun DayWeatherInfo(day: String, max: String, min: String, icon: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextDislay(day, MaterialTheme.typography.titleMedium.fontSize)
        Spacer(Modifier.height(5.dp))
        DisplayIcon(getWeatherIcon(icon), IconSize.small)
        Spacer(Modifier.height(8.dp))
        TextDislay(max+"º", MaterialTheme.typography.bodyLarge.fontSize)
        TextDislay(min+"º", MaterialTheme.typography.bodyMedium.fontSize, Modifier.alpha(0.8f))
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

@RequiresApi(Build.VERSION_CODES.O)
fun getDayOfWeek(dateTimeString: String): String {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    val dateTime = LocalDateTime.parse(dateTimeString, formatter)

    val dayOfWeek: DayOfWeek = dateTime.dayOfWeek

    val dayName = dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, Locale("es", "ES"))
        .first().uppercase()

    return if (dayOfWeek == DayOfWeek.WEDNESDAY) {
        "X"
    } else {
        dayName
    }
}