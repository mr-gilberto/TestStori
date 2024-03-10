package com.gilberto.test.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gilberto.test.features.login.LoginRoute
import com.gilberto.test.features.media.TakePictureRoute
import com.gilberto.test.features.register.RegisterRoute
import com.gilberto.test.features.splash.SplashRoute

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
) {


    NavHost(navController = navController, startDestination = SplashRoute.ROUTE) {
        SplashRoute().let { screen ->
            composable(screen.route, screen.arguments) {
                screen.Route(navController)
            }
        }

        LoginRoute().let { screen ->
            composable(screen.route, screen.arguments) {
                screen.Route(navController)
            }
        }

        RegisterRoute().let { screen ->
            composable(screen.route, screen.arguments) {
                screen.Route(navController)
            }
        }

        TakePictureRoute().let { screen ->
            composable(screen.route, screen.arguments) {
                screen.Route(navController)
            }
        }
    }
}
