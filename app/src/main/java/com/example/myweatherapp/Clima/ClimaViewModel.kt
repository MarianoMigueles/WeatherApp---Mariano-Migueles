package com.example.myweatherapp.Clima

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ClimaViewModel : ViewModel() {
    var estado by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)

    fun Ejecutar(intencion: ClimaIntencion) {
        when(intencion) {
            is ClimaIntencion.Actualizar -> Actualizar()
        }
    }

    private fun Actualizar() {
        estado = ClimaEstado.Cargando
        estado = ClimaEstado.Error("No funciono el servidor, esta todo roto")
    }
}