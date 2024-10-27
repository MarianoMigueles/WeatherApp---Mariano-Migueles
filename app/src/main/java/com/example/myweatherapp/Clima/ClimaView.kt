package com.example.myweatherapp.Clima

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CiudadView(
    modifier: Modifier = Modifier,
    estado: ClimaEstado,
    ejecutar: (ClimaIntencion) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        when(estado) {
            is ClimaEstado.Cargando -> CargandoView()
            is ClimaEstado.Error ->  ErrorView(estado.mensaje)
            is ClimaEstado.Exito -> ExitosoView()
            is ClimaEstado.Vacio -> VacioView()
        }
    }
}

@Composable
fun VacioView() {
    Text("Vacio")
}

@Composable
fun ExitosoView() {
    Text("Exito al cargar")
}

@Composable
fun ErrorView(mensaje: String) {
    Text(
        mensaje,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.error
    )
}

@Composable
fun CargandoView() {
    Text("Cargando")
}
