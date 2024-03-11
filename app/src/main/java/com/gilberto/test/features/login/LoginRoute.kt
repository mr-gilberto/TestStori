package com.gilberto.test.features.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gilberto.test.navigation.Route
import com.gilberto.test.features.home.main.HomeRoute
import com.gilberto.test.features.register.RegisterRoute

class LoginRoute : Route(ROUTE) {

    @Composable
    fun Route(navController: NavHostController) {
        val viewModel: LoginViewModel = hiltViewModel()
        val viewState = viewModel.uiState.collectAsState()

        LoginScreen(
            viewState.value.loading,
            viewState.value.errorState,
            onActionSnackBar = {
                viewModel.onActionSnackBar()
            },
            onLogIn = { user, password ->
                viewModel.login(user, password)
            },
            onRegister = {
                navController.navigate(RegisterRoute.ROUTE) {

                }
            }
        )

        LaunchedEffect(key1 = viewState.value.navigateToMainView) {
            if (viewState.value.navigateToMainView) {
                navController.navigate(HomeRoute.ROUTE) {
                    launchSingleTop = true
                    popUpTo(ROUTE) {
                        inclusive = true
                    }
                }
                viewModel.onNavigate()
            }
        }
    }

    companion object {
        const val ROUTE = "LoginRoute"
    }
}
