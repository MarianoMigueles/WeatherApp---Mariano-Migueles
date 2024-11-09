package com.example.myweatherapp.presentation.weather.weatherView

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.myweatherapp.icons.IconManager
import com.example.myweatherapp.presentation.EventImage
import com.example.myweatherapp.presentation.EventText
import com.example.myweatherapp.presentation.EventView

@Composable
fun EmptyView() {
    EventView {
        EventImage(IconManager.emptyIcon)
        EventText("¡Se lo llevo el viento!")
        Spacer(modifier = Modifier.height(1.dp))
        EventText("No hay nada aqui")
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyViewPreview() {
    AppTheme {
        EmptyView()
    }
}