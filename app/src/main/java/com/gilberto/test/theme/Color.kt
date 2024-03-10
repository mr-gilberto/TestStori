package com.gilberto.test.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import kotlin.random.Random


object Colors {
    val ColorTransparent = Color(0x00000000)
    val ColorPrimary = Color(0xFFFFFFFF)
    val ColorPrimaryDark = Color(0xFFFFFFFF)
    val ColorAccent = Color(0xFF2596BE)
    val SplashColor = Color(0xB32596BE)
    val ColorSecondary = Color(0xFF39BDED)
    val ColorTertiary = Color(0xFF00B9FF)
    val ColorBlack = Color(0xFF000000)
    val ColorWhite = Color(0xFFFFFFFF)
    val ColorBackground = Color(0xFFF0F0F0)
    val ColorGrayText1 = Color(0xFF9B9B9B)
    val ColorTransparentBlack = Color(0x7F000000)
    val ColorBackgroundGradientStart = Color(0xFF000000)
    val ColorBackgroundGradientEnd = Color(0xFFDDDDDD)
    val ColorFastlaneBackground = Color(0xFF0096A6)
    val ColorSearchOpaque = Color(0xFFB32828)
    val ColorSelectedBackground = Color(0xFFFFAA3F)
    val ColorDefaultBackground = Color(0xFF3D3D3D)
    val ColorGrayLight = Color(0xFF999999)
    val ColorGrayDark = Color(0xFF333333)
    val ColorGrayRow = Color(0xFFB5B5B5)
    val ColorTextColorInputText = Color(0xFF212121)
    val ColorBlackOp5 = Color(0x0D000000)
    val ColorBlackOp10 = Color(0x1A000000)
    val ColorBlackOp20 = Color(0x33000000)
    val ColorBlackOp50 = Color(0x80000000)
    val ColorBlackOp60 = Color(0x99000000)
    val ColorBlackOp70 = Color(0xB3000000)
    val ColorBlackOp90 = Color(0xE6000000)
    val ColorWhite40 = Color(0x66FFFFFF)
    val ColorWhite30 = Color(0x4DFFFFFF)

    val ColorCards = Color(0xE8FFFFFF)

    val colorInProgress = Color(0xFF2596BE)
    val colorNotStarted = Color(0xFCFF7474)

    val cardAccent = Color(0xFF2596BE)
    val lightCardAccent = Color(0xFF006685)
    val lightOnCardAccent = Color(0xFFFFFFFF)
    val lightCardAccentContainer = Color(0xFFEFFBFD)
    val lightOnCardAccentContainer = Color(0xFF001F2A)
    val darkCardAccent = Color(0xFF6AD3FF)
    val darkOnCardAccent = Color(0xFF003546)
    val darkCardAccentContainer = Color(0xFF004D65)
    val darkOnCardAccentContainer = Color(0xFFBEE9FF)


    fun getRandomColorFromColorsObject(): Color {
        val colorList = listOf(
            Color(0xFF4FC3F7),
        )

        return colorList[Random.nextInt(colorList.size)]
    }
}



val md_theme_light_primary = Color(0xFF2596BE)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFCBE6FF)
val md_theme_light_onPrimaryContainer = Color(0xFF001E30)
val md_theme_light_secondary = Color(0xFF50606F)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFFD4E4F6)
val md_theme_light_onSecondaryContainer = Color(0xFF0C1D29)
val md_theme_light_tertiary = Color(0xFF00687A)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFACEDFF)
val md_theme_light_onTertiaryContainer = Color(0xFF001F26)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFCFCFF)
val md_theme_light_onBackground = Color(0xFF1A1C1E)
val md_theme_light_surface = Color(0xFFFCFCFF)
val md_theme_light_onSurface = Color(0xFF1A1C1E)
val md_theme_light_surfaceVariant = Color(0xFFDEE3EA)
val md_theme_light_onSurfaceVariant = Color(0xFF41474D)
val md_theme_light_outline = Color(0xFF72787E)
val md_theme_light_inverseOnSurface = Color(0xFFF0F0F3)
val md_theme_light_inverseSurface = Color(0xFF2E3133)
val md_theme_light_inversePrimary = Color(0xFF8FCDFF)
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFF006495)
val md_theme_light_outlineVariant = Color(0xFFC1C7CE)
val md_theme_light_scrim = Color(0xFF000000)

val MaterialLightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)

val darkExtendedColors = ExtendedColors(
    neutralSurface = Color(0x14FFFFFF),
    colorOnCustom = Color(0xFFFFFFFF),
    colorOnCustomVariant = Color(0xB3FFFFFF),
    colorSurface1 = Color(0xFF23242A),
    colorSurface2 = Color(0xFF272A31),
    colorSurface3 = Color(0xFF2C2F37),
    colorSurface4 = Color(0xFF2E3039),
    colorSurface5 = Color(0xFF31343E),
    colorTransparent1 = Color(0x0AFFFFFF),
    colorTransparent2 = Color(0x1FFFFFFF),
    colorTransparent3 = Color(0x29FFFFFF),
    colorTransparent4 = Color(0x7AFFFFFF),
    colorTransparent5 = Color(0xB8FFFFFF),
    colorNeutral = Color(0xFF121212),
    colorNeutralVariant = Color(0xFF5C5C5C),
    colorTransparentInverse1 = Color(0x0A000000),
    colorTransparentInverse2 = Color(0x14000000),
    colorTransparentInverse3 = Color(0x29000000),
    colorTransparentInverse4 = Color(0xB8000000),
    colorTransparentInverse5 = Color(0xF5000000),
    colorNeutralInverse = Color(0xE0FFFFFF),
    colorNeutralVariantInverse = Color(0xA3FFFFFF),
)



@Immutable
data class ExtendedColors(
    val neutralSurface: Color,
    val colorOnCustom: Color,
    val colorOnCustomVariant: Color,
    val colorSurface1: Color,
    val colorSurface2: Color,
    val colorSurface3: Color,
    val colorSurface4: Color,
    val colorSurface5: Color,
    val colorTransparent1: Color,
    val colorTransparent2: Color,
    val colorTransparent3: Color,
    val colorTransparent4: Color,
    val colorTransparent5: Color,
    val colorNeutral: Color,
    val colorNeutralVariant: Color,
    val colorTransparentInverse1: Color,
    val colorTransparentInverse2: Color,
    val colorTransparentInverse3: Color,
    val colorTransparentInverse4: Color,
    val colorTransparentInverse5: Color,
    val colorNeutralInverse: Color,
    val colorNeutralVariantInverse: Color,
)

val LocalExtendedColors = staticCompositionLocalOf {
    ExtendedColors(
        neutralSurface = Color.Unspecified,
        colorOnCustom = Color.Unspecified,
        colorOnCustomVariant = Color.Unspecified,
        colorSurface1 = Color.Unspecified,
        colorSurface2 = Color.Unspecified,
        colorSurface3 = Color.Unspecified,
        colorSurface4 = Color.Unspecified,
        colorSurface5 = Color.Unspecified,
        colorTransparent1 = Color.Unspecified,
        colorTransparent2 = Color.Unspecified,
        colorTransparent3 = Color.Unspecified,
        colorTransparent4 = Color.Unspecified,
        colorTransparent5 = Color.Unspecified,
        colorNeutral = Color.Unspecified,
        colorNeutralVariant = Color.Unspecified,
        colorTransparentInverse1 = Color.Unspecified,
        colorTransparentInverse2 = Color.Unspecified,
        colorTransparentInverse3 = Color.Unspecified,
        colorTransparentInverse4 = Color.Unspecified,
        colorTransparentInverse5 = Color.Unspecified,
        colorNeutralInverse = Color.Unspecified,
        colorNeutralVariantInverse = Color.Unspecified,
    )
}
