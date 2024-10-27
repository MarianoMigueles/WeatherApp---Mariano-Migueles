package com.example.myweatherapp.Clima

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ClimaPage(
    modifier: Modifier = Modifier
) {
    val viewModel : ClimaViewModel = viewModel<ClimaViewModel>()
    CiudadView(
        modifier = Modifier,
        estado = viewModel.estado,
    ) {
        viewModel.Ejecutar(it)
    }
}