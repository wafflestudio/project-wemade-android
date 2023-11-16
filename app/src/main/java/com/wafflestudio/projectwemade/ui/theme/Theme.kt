package com.wafflestudio.projectwemade.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.wafflestudio.projectwemade.ui.theme.WemadeColors.Blue50
import com.wafflestudio.projectwemade.ui.theme.WemadeColors.Red50
import com.wafflestudio.projectwemade.ui.theme.WemadeColors.Yellow50

private val DarkColorScheme = darkColorScheme(
    primary = Blue50,
    secondary = Red50,
    tertiary = Yellow50
)

private val LightColorScheme = lightColorScheme(
    primary = Blue50,
    secondary = Red50,
    tertiary = Yellow50

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ProjectWemadeAndroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}