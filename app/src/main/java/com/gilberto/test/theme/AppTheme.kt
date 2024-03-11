package com.gilberto.test.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun AppTheme(
    isDarkMode: Boolean = false,
    content: @Composable () -> Unit,
) {
    val extendedColors = if (isSystemInDarkTheme()) darkExtendedColors else darkExtendedColors

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = MaterialLightColors,
            typography = typography,
            content = content,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TypographyPreview() {
    AppTheme(isDarkMode = false) {
        Column {
            Text(
                text = "Headline Small",
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                text = "Headline Small",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "Headline Small",
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = "Title Large",
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "Title Medium",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "Title Small",
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                text = "Body Large",
                style = MaterialTheme.typography.bodyLarge,
            )
            Text(
                text = "Body Medium",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Body Small",
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = "Label Large",
                style = MaterialTheme.typography.labelLarge,
            )
            Text(
                text = "Label Medium",
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                text = "Label Small",
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}