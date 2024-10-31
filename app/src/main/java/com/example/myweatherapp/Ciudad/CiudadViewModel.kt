package com.example.myweatherapp.Ciudad

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CiudadViewModel() : ViewModel() {
    var estado by mutableStateOf<CiudadEstado>(CiudadEstado.Vacio)

    fun ejecutar(intencion: CiudadIntencion) {
        when(intencion) {
            is CiudadIntencion.Actualizar -> cargarCiudades()
        }
    }

    private fun cargarCiudades() {
        estado = CiudadEstado.Cargando
        viewModelScope.launch {

        }
        estado = CiudadEstado.Error("No funciono el servidor, esta todo roto")
    }
}