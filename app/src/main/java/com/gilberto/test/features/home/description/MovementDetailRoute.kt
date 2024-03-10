package com.gilberto.test.features.home.description

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.gilberto.navigation.Route
import com.gilberto.test.util.Arguments
import com.integrapsim.features.service.description.MovementDetail

class MovementDetailRoute(
    arguments: List<NamedNavArgument> = listOf(
        navArgument(Arguments.MOVEMENT_ID) {
            type = NavType.StringType
        },
    ),
) : Route(ROUTE, arguments) {

    @Composable
    fun Route(navController: NavHostController, movementId: String) {
        val viewModel: MovementDetailViewModel = hiltViewModel()
        val viewState = viewModel.uiState.collectAsState()

        MovementDetail(
            viewState.value.movement,
            onBackPressed = {
                navController.popBackStack()
            },
        )

        viewModel.getMovement(movementId)
    }

    companion object {
        private const val VIEW = "MovementDetail"
        private const val SEGMENT = "/{${Arguments.MOVEMENT_ID}}"
        const val ROUTE = VIEW + SEGMENT

        fun getRoute(movementId: String): String {
            return "$VIEW/$movementId"
        }
    }
}
