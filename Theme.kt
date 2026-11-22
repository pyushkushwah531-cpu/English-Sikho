package com.englishseekho.pro.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val EmeraldPrimary = Color(0xFF0F5C4D)
val EmeraldLight = Color(0xFFE6F4F0)
val AmberAccent = Color(0xFFB45309)
val AmberLight = Color(0xFFFEF3C7)
val SlateInk = Color(0xFF0F172A)
val SlateMuted = Color(0xFF475569)
val CanvasPaper = Color(0xFFF7F9F8)
val CardSurface = Color(0xFFFFFFFF)
val HairlineBorder = Color(0xFFE2E8F0)
val ErrorRed = Color(0xFFDC2626)
val ErrorSoft = Color(0xFFFEE2E2)
val SuccessGreen = Color(0xFF059669)
val SuccessSoft = Color(0xFFD1FAE5)

private val LightColors = lightColorScheme(
    primary = EmeraldPrimary,
    onPrimary = Color.White,
    primaryContainer = EmeraldLight,
    onPrimaryContainer = EmeraldPrimary,
    secondary = AmberAccent,
    onSecondary = Color.White,
    secondaryContainer = AmberLight,
    onSecondaryContainer = AmberAccent,
    background = CanvasPaper,
    onBackground = SlateInk,
    surface = CardSurface,
    onSurface = SlateInk,
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = SlateMuted,
    outline = HairlineBorder
)

@Composable
fun EnglishSeekhoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content
    )
}
