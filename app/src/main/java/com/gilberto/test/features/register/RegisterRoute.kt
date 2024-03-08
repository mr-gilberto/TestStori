package com.gilberto.test.features.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gilberto.navigation.Route

class RegisterRoute : Route(ROUTE) {

    @Composable
    fun Route(navController: NavHostController) {
        val viewModel: RegisterViewModel = hiltViewModel()
        val viewState = viewModel.uiState.collectAsState()

        RegisterScreen(
            viewModel.registerFormState,
            viewModel::onViewEvent,
            viewState.value.loading,
            viewState.value.errorState,
        )

        LaunchedEffect(key1 = viewState.value.navigateToMainView) {
            if (viewState.value.navigateToMainView) {
                /*     navController.navigate(HomeRoute.ROUTE) {
                         launchSingleTop = true
                         popUpTo(ROUTE) {
                             inclusive = true
                         }
                     } */
                viewModel.onNavigate()
            }
        }
    }


    companion object {
        const val ROUTE = "RegisterRoute"
    }
}
