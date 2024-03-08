package com.gilberto.test.features.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.gilberto.test.R
import com.gilberto.test.theme.AppTheme
import com.gilberto.test.theme.Colors

@Preview
@Composable
fun LoginScreenPreview() {
    val fakeNavController = rememberNavController()
    AppTheme {
        SplashScreen()
    }
}

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.SplashColor),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.workers),
            modifier = Modifier
                .height(185.dp)
                .width(185.dp),
            contentDescription = null,
        )
    }
}
