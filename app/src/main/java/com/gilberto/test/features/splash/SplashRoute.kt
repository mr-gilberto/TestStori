/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gilberto.test.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gilberto.test.features.login.LoginRoute
import com.gilberto.navigation.Route
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
                    navController.navigate(LoginRoute.ROUTE) {
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
