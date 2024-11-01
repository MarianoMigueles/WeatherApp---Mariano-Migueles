package com.example.myweatherapp.Presentation

import androidx.compose.foundation.Image
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
import com.example.myweatherapp.R
import com.example.myweatherapp.ui.theme.fontInknutAntiqua

@Composable
fun EventView(content: @Composable () -> Unit) {
    val modifier: Modifier = Modifier

    Column(
        modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
fun EventText(text: String, colorType: String = "default") {
    Text(
        text,
        color = MaterialTheme.colorScheme.primary,
        fontFamily = fontInknutAntiqua,
        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
        fontWeight = MaterialTheme.typography.headlineLarge.fontWeight
    )
}

@Composable
fun EventImage(iconState: String, description: String) {

    val iconResId = when (iconState) {
        "empty" -> R.drawable.wind
        "error" -> R.drawable.error
        "load" -> R.drawable.loading
        else -> null
    }

    if (iconResId != null) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = description,
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

