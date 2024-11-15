package com.example.myweatherapp.presentation.events

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme
import com.example.myweatherapp.icons.IconManager

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