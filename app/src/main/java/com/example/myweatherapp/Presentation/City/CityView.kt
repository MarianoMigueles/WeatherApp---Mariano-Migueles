package com.example.myweatherapp.Presentation.City

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.Presentation.EventImage
import com.example.myweatherapp.Presentation.EventText
import com.example.myweatherapp.Presentation.EventView


@Composable
fun CityView(
    modifier: Modifier = Modifier,
    state: CityState,
    execute: (CityIntention) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        when(state) {
            is CityState.Loading -> LoadingView()
            is CityState.Error ->  ErrorView(state.message)
            is CityState.Success -> SuccessfulView()
            is CityState.Empty -> EmptyView()
        }
    }
}

@Composable
fun EmptyView() {
    EventView {
        EventImage("empty", "Icono de contenido vacio.")
        EventText("¡Se lo llevo el viento!")
        Spacer(modifier = Modifier.height(1.dp))
        EventText("No hay nada aqui")
    }
}

@Composable
fun SuccessfulView() {
    Text("Exito al cargar")
}

@Composable
fun ErrorView(message: String) {
    EventView {
        EventImage("error", "Icono de error.")
        EventText("¡Ha ocurrido un error!")
        Spacer(modifier = Modifier.height(1.dp))
        Text(message)
    }
}

@Composable
fun LoadingView() {
    EventView {
        EventImage("load", "Icono de contenido cargando.")
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