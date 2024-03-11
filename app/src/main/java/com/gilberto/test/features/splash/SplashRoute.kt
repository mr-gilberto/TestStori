package com.gilberto.test.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gilberto.test.navigation.Route
import com.gilberto.test.features.home.main.HomeRoute
import com.gilberto.test.features.login.LoginRoute
import kotlinx.coroutines.delay

class SplashRoute : Route(ROUTE) {

    @Composable
    fun Route(navController: NavHostController) {
        val viewModel: SplashViewModel = hiltViewModel()
        val viewState = viewModel.uiState.collectAsState()

        LaunchedEffect(true) {
            viewModel.observeUserInfo()
        }

        SplashScreen()

        LaunchedEffect(viewState.value.isLoggedIn) {
            when (viewState.value.isLoggedIn) {
                true -> {
                    delay(600)
                    navController.navigate(HomeRoute.ROUTE) {
                        popUpTo(ROUTE) {
                            inclusive = true
                        }
                    }
                }

                false -> {
                    delay(600)
                    navController.navigate(LoginRoute.ROUTE) {
                        popUpTo(ROUTE) {
                            inclusive = true
                        }
                    }
                }

                else -> Unit
            }
        }
    }

    companion object {
        const val ROUTE = "ROUTE_SPLASH"
    }
}
