package com.example.myweatherapp.presentation.city

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.icons.IconManager
import com.example.myweatherapp.presentation.EventImage
import com.example.myweatherapp.presentation.EventText
import com.example.myweatherapp.presentation.EventView
import com.example.myweatherapp.presentation.PageView
import com.example.myweatherapp.repository.MoackRepository.cities
import com.example.myweatherapp.repository.models.City


@Composable
fun CityView(
    modifier: Modifier = Modifier,
    state: CityState,
    execute: (CityIntention) -> Unit
) {
    when(state) {
        is CityState.Loading -> LoadingView()
        is CityState.Error ->  ErrorView(state.message)
        is CityState.Success -> SuccessfulView()
        is CityState.Empty -> EmptyView()
    }
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
fun SuccessfulView() {
    val input = remember { mutableStateOf("") }
    val selectedCity = remember { mutableStateOf<City?>(null) }

    PageView { modifier ->
        Column (
            modifier = modifier.padding(15.dp)
        ) {
            TextField(
                value = input.value,
                onValueChange = { newText -> input.value = newText },
                label = { Text("Texto") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ErrorView(message: String) {
    EventView {
        EventImage(IconManager.errorIcon)
        EventText("¡Ha ocurrido un error!", "error")
        Spacer(modifier = Modifier.height(1.dp))
        Text(message)
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

@Preview(showBackground = true)
@Composable
fun SuccessfulViewPreview() {
    MaterialTheme {
        SuccessfulView()
    }

}