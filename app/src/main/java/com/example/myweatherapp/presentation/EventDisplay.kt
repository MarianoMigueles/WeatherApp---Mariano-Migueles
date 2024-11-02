package com.example.myweatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myweatherapp.icons.models.IconModel
import com.example.ui.theme.displayFontFamily

@Composable
fun EventView(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
    }
}

@Composable
fun EventText(text: String, colorType: String = "default") {

    val colorScheme = when(colorType) {
        "error" -> MaterialTheme.colorScheme.error
        else -> MaterialTheme.colorScheme.primary
    }

    Text(
        text,
        color = colorScheme,
        fontFamily = displayFontFamily,
        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
        fontWeight = MaterialTheme.typography.headlineLarge.fontWeight
    )
}

@Composable
fun EventImage(icon: IconModel? = null) {

    if (icon != null) {
        Image(
            painter = painterResource(id = icon.imageVector),
            contentDescription = icon.description,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .aspectRatio(1f)
                .alpha(0.9f),
            contentScale = ContentScale.Crop
        )
    } else {
        Text("Icono no encontrado")
    }

}

