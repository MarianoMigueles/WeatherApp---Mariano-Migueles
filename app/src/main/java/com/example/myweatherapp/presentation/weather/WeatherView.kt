package com.example.myweatherapp.presentation.weather

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.icons.IconManager
import com.example.myweatherapp.presentation.EventImage
import com.example.myweatherapp.presentation.EventText
import com.example.myweatherapp.presentation.EventView

@Composable
fun WeatherView(
    modifier: Modifier = Modifier,
    state: WeatherState,
    execute: (WeatherIntention) -> Unit
) {
    when(state) {
        is WeatherState.Loading -> LoadingView()
        is WeatherState.Error ->  ErrorView(state.message)
        is WeatherState.Success -> SuccessfulView()
        is WeatherState.Empty -> EmptyView()
    }
}

@Composable
fun SuccessfulView() {
    Text("Exito al cargar")
}

@Composable
fun EmptyView() {
    EventView {
        EventImage(IconManager.emptyIcon)
        EventText("¡Se lo llevo el viento!")
        Spacer(modifier = Modifier.height(1.dp))
        EventText("No hay nada aqui")
    }
}

@Composable
fun ErrorView(message: String) {
    EventView {
        EventImage(IconManager.errorIcon)
        EventText("¡Ha ocurrido un error!", "error")
        Spacer(modifier = Modifier.height(1.dp))
        EventText(message, "error")
    }
}

@Composable
fun LoadingView() {
    EventView {
        EventImage(IconManager.loadingIcon)
        EventText("Cargando...")
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyViewPreview() {
    MaterialTheme {
        EmptyView()
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingViewPreview() {
    MaterialTheme {
        LoadingView()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorViewPreview() {
    MaterialTheme {
        ErrorView("Error")
    }
}