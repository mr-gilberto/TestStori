package com.gilberto.test.features.media.photo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavHostController
import com.gilberto.navigation.Route

class PhotoScreenRoute(
    arguments: List<NamedNavArgument> = listOf(

    ),
) : Route(ROUTE, arguments) {

    @Composable
    fun Route(
        navController: NavHostController,

        ) {
        val viewModel: PhotoScreenViewModel = hiltViewModel()
        val state = viewModel.uiState.collectAsState()

        PhotoScreen(
            viewModel,
        )

        if (state.value.successPhotoTaken) {
            LaunchedEffect(state.value.successPhotoTaken) {

                viewModel.onSuccessPhotoTaken()
            }
        }

        LaunchedEffect(state.value.onCloseTapped) {
            if (state.value.onCloseTapped) {
                navController.navigateUp()
            }
        }
    }

    companion object {
        private const val VIEW = "PhotoScreenRoute"
        private const val SEGMENT1 = ""
        const val ROUTE = VIEW + SEGMENT1

        fun getRoute(
            image: String,
        ): String {
            return ROUTE
        }
    }
}
