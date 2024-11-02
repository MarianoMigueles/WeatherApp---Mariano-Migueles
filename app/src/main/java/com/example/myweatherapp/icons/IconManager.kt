package com.example.myweatherapp.icons

import com.example.myweatherapp.R
import com.example.myweatherapp.icons.models.IconModel

object IconManager {
    val shareIcon = IconModel(
        name = "share",
        imageVector = R.drawable.share,
        description = "Icono de compartir"
    )

    val settingsIcon = IconModel(
        name = "settings",
        imageVector = R.drawable.settings,
        description = "Icono de ajustes"
    )

    val windIcon = IconModel(
        name = "wind",
        imageVector = R.drawable.wind,
        description = "Icono de viento"
    )

    val errorIcon = IconModel(
        name = "error",
        imageVector = R.drawable.error,
        description = "Icono de error"
    )

    val loadingIcon = IconModel(
        name = "loading",
        imageVector = R.drawable.loading,
        description = "Icono de carga"
    )

    val emptyIcon = IconModel(
        name = "empty",
        imageVector = R.drawable.wind,
        description = "Icono de vacio"
    )
}