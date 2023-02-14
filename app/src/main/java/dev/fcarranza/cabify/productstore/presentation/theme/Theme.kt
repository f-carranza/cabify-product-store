package dev.fcarranza.cabify.productstore.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = CabifyProductStoreLight,
    primaryVariant = CabifyProductStorePrimary,
    background = BackgroundDark,
)

private val LightColorPalette = lightColors(
    primary = CabifyProductStorePrimary,
    primaryVariant = CabifyProductStoreDark,
    background = BackgroundLight,
)

@Composable
fun CabifyProductStoreTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}