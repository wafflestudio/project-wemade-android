package com.wafflestudio.projectwemade.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.wafflestudio.projectwemade.theme.WemadeColors.Black900
import com.wafflestudio.projectwemade.theme.WemadeColors.ExtraLightGray
import com.wafflestudio.projectwemade.theme.WemadeColors.MainGreen
import com.wafflestudio.projectwemade.theme.WemadeColors.White900

private val DarkColorScheme = darkColorScheme(
    primary = MainGreen,
    secondary = White900,
    background = ExtraLightGray,
    surface = White900,
    onSurface = Black900
)

private val LightColorScheme = lightColorScheme(
    primary = MainGreen,
    secondary = White900,
    background = ExtraLightGray,
    surface = White900,
    onSurface = Black900

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