package com.gilberto.test.features.home.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gilberto.test.navigation.Route
import com.gilberto.test.features.home.description.MovementDetailRoute
import com.gilberto.test.features.login.LoginRoute

class HomeRoute : Route(ROUTE) {

    @Composable
    fun Route(navController: NavHostController) {
        val viewModel: HomeViewModel = hiltViewModel()
        val viewState = viewModel.uiState.collectAsState()

        LaunchedEffect(key1 = viewState.value.logOut, block = {
            if (viewState.value.logOut) {
                navController.navigate(LoginRoute.ROUTE) {
                    popUpTo(ROUTE) {
                        inclusive = true
                    }
                }
            }
        })

        HomeScreen(
            viewState.value,
            onItemMenuClick = {
                viewModel.logOut()
            },
            onMovementDetail = {
                navController.navigate(MovementDetailRoute.getRoute(it.documentId))
            }
        )
    }

    companion object {
        const val ROUTE = "ROUTE_HOME"
    }
}
