package com.example.myweatherapp.presentation.city.cityView

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import com.example.myweatherapp.icons.IconManager
import com.example.myweatherapp.presentation.EventImage
import com.example.myweatherapp.presentation.EventText
import com.example.myweatherapp.presentation.EventView

@Composable
fun LoadingView() {
    EventView {
        EventImage(IconManager.loadingIcon)
        EventText("Cargando...")
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingViewPreview() {
    AppTheme {
        LoadingView()
    }
}