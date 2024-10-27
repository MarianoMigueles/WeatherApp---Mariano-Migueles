package com.example.myweatherapp.Ciudad

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CiudadViewModel : ViewModel() {
    var estado by mutableStateOf<CiudadEstado>(CiudadEstado.Vacio)

    fun ejecutar(intencion: CiudadIntencion) {
        when(intencion) {
            is CiudadIntencion.Actualizar -> actualizar()
        }
    }

    private fun actualizar() {
        estado = CiudadEstado.Cargando
        estado = CiudadEstado.Error("No funciono el servidor, esta todo roto")
    }
}