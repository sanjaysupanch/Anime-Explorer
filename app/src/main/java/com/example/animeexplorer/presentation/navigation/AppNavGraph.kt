package com.example.animeexplorer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.animeexplorer.presentation.components.AnimeDetailScreen
import com.example.animeexplorer.presentation.components.AnimeListScreen

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AnimeList.route
    ) {

        composable(route = Screen.AnimeList.route) {
            AnimeListScreen(
                onClick = { animeId ->
                    navController.navigate(
                        Screen.AnimeDetail.createRoute(animeId)
                    )
                }
            )
        }

        composable(
            route = Screen.AnimeDetail.route,
            arguments = listOf(
                navArgument("animeId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val animeId =
                backStackEntry.arguments?.getInt("animeId") ?: return@composable

            AnimeDetailScreen(id = animeId)
        }
    }
}