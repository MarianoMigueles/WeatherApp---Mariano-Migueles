package com.example.myweatherapp.Ciudad

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CiudadPage(
    modifier: Modifier = Modifier
) {
    val viewModel : CiudadViewModel = viewModel<CiudadViewModel>()
    CiudadView(
        modifier = Modifier,
        estado = viewModel.estado,
    ) {
        viewModel.ejecutar(it)
    }
}