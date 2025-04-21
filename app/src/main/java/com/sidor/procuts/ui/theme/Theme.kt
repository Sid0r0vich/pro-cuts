package com.sidor.procuts.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
)

private val LightColorScheme = lightColorScheme(
    background = backgroundLightColor,
    surfaceVariant = cardLightColor,
    secondaryContainer = Color.Black,
    tertiaryContainer = Color.Black,
    primaryContainer = Color.Black,
    onSecondaryContainer = Color.Black,
    onTertiaryContainer = Color.Black,
    onPrimaryContainer = Color.Black

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

val LocalColorPalette = compositionLocalOf<ColorPalette> { error("no value provided") }

@Composable
fun ProCutsTheme(
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//            dynamicLightColorScheme(context)
//        }

        //darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val colorPalette = when {
        darkTheme -> baseDarkPalette
        else -> baseLightPalette
    }

    CompositionLocalProvider(LocalColorPalette provides colorPalette) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}