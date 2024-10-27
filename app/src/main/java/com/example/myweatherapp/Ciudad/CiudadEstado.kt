package com.example.myweatherapp.Ciudad

sealed class CiudadEstado {
    data object Vacio : CiudadEstado()
    data object Cargando : CiudadEstado()
    data class Exito(val ciudad: Ciudad) : CiudadEstado()
    data class Error(val mensaje: String) : CiudadEstado()
}

class Ciudad() {
    var nombre: String = "Sao Pablo"
}