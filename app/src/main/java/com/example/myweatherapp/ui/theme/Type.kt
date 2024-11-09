package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import com.example.myweatherapp.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val displayFontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Inknut Antiqua"),
        fontProvider = provider
    )
)

// Default Material 3 typography values
val baseline = Typography()

val AppTypography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily), // Temperatura grande ("28°") //h1
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily), // Unidad de temperatura ("°C") //span
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily), // Descripción del clima ("Nublado") //h2
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily), // Nombre de la ciudad ("Ciudad...") //h3
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily), // Días de la semana (D, L, M, X, J, V, S) //h4
    bodyLarge = baseline.bodyLarge.copy(fontFamily = displayFontFamily), // Temperaturas diarias pequeñas en los círculos //span
)

//displayLarge (h1) para el elemento más destacado, la temperatura.
//displayMedium (span) para la unidad de temperatura (°C), como texto secundario alineado con el h1.
//headlineLarge (h2) para la descripción del clima.
//headlineMedium (h3) para el nombre de la ciudad.
//titleLarge (h4) para los días de la semana.
//bodyLarge (span) para las temperaturas diarias pequeñas, sin jerarquía de encabezado, ya que son datos secundarios.