package com.example.myweatherapp.Clima

sealed class ClimaEstado {
    data object Vacio : ClimaEstado()
    data object Cargando : ClimaEstado()
    data class Exito(val clima: Clima) : ClimaEstado()
    data class Error(val mensaje: String) : ClimaEstado()
}

class Clima() {
    var nombre: String = "Sao Pablo"
}