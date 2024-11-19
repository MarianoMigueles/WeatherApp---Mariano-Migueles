package com.example.compose
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import com.example.ui.theme.AppTypography
import com.example.ui.theme.getWindowSize

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    secondary = secondaryLight,
    error = errorLight,
    background = backgroundLight,
    onBackground = onBackgroundCardLight
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    secondary = secondaryDark,
    error = errorDark,
    background = backgroundDark,
    onBackground = onBackgroundCardDark
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      darkTheme -> darkScheme
      else -> lightScheme
  }

    val windowSize = getWindowSize()

  MaterialTheme(
    colorScheme = colorScheme,
    typography = AppTypography(windowSize),
    content = content
  )
}

