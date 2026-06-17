package com.tehranalaarm.app.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFD32F2F),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFB71C1C),
    onPrimaryContainer = Color.White,
    secondary = Color(0xFFFFC107),
    onSecondary = Color.Black,
    secondaryContainer = Color(0xFFFFA000),
    onSecondaryContainer = Color.Black,
    tertiary = Color(0xFF121212),
    onTertiary = Color.White,
    background = Color(0xFF0A0A0A),
    onBackground = Color.White,
    surface = Color(0xFF1E1E1E),
    onSurface = Color.White,
    error = Color(0xFFD32F2F),
    onError = Color.White,
    errorContainer = Color(0xFFB71C1C),
    onErrorContainer = Color.White,
    outline = Color(0xFF303030),
)

@Composable
fun TehranAlarmTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = TehranAlarmTypography,
        content = content
    )
}
