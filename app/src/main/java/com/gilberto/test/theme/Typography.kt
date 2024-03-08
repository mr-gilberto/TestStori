package com.gilberto.test.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp

val typography = Typography().run {
    copy(
        headlineLarge = headlineLarge.copy(
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp,
        ),
        headlineMedium = headlineMedium.copy(
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
        ),
        titleLarge = titleLarge.copy(
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp,
        ),
        titleMedium = titleMedium.copy(
            fontSize = 18.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.0125.sp,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
        ),
        titleSmall = titleSmall.copy(
            fontSize = 16.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.0125.sp,
        ),
        bodyLarge = bodyLarge.copy(
            fontSize = 16.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.0125.sp,
        ),
        bodyMedium = bodyMedium.copy(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.0107.sp,
        ),
        bodySmall = bodySmall.copy(
            fontSize = 13.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.0192.sp,
        ),
        labelLarge = labelLarge.copy(
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.0107.sp,
        ),
        labelMedium = labelMedium.copy(
            fontSize = 13.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.0192.sp,
        ),
        labelSmall = labelSmall.copy(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.025.sp,
        ),
    )
}
