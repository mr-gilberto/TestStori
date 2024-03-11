package com.gilberto.test.features.media

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gilberto.test.navigation.Route
import com.gilberto.test.features.home.main.HomeRoute

class TakePictureRoute : Route(ROUTE) {

    @Composable
    fun Route(navController: NavHostController) {
        val viewModel: TakePictureViewModel = hiltViewModel()
        val viewState = viewModel.uiState.collectAsState()

        TakePictureScreen(
            viewModel::uploadFile
        )

        LaunchedEffect(key1 = viewState.value.successTakePicture) {
            if (viewState.value.successTakePicture) {
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
        const val ROUTE = "TakePicture"
    }
}
