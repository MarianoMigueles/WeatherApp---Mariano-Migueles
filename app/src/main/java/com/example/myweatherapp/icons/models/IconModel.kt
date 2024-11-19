package com.example.myweatherapp.icons.models

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class IconModel(
    val name: String,
    val imageVector: Int,
    val description: String,
    val size: Dp = IconSize.small
)

object IconSize {
    val extraSmall = 26.dp
    val small = 38.dp
    val medium = 72.dp
    val large = 96.dp
    val extraLarge = 150.dp
}


