package com.example.animeexplorer.presentation.navigation

sealed class Screen(val route: String) {

    object AnimeList : Screen("anime_list")

    object AnimeDetail : Screen("anime_detail/{animeId}") {
        fun createRoute(animeId: Int): String =
            "anime_detail/$animeId"
    }
}