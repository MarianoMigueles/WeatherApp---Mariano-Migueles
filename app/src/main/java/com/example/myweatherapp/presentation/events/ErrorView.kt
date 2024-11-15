package com.example.myweatherapp.presentation.events

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AppTheme
import com.example.myweatherapp.icons.IconManager

@Composable
fun ErrorView(message: String) {
    EventView {
        EventImage(IconManager.errorIcon)
        EventText("¡Ha ocurrido un error!", "error")
        Spacer(modifier = Modifier.height(1.dp))
        EventText(message, "error")
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorViewPreview() {
    AppTheme {
        ErrorView("Error")
    }
}