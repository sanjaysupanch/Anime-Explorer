package com.example.animeexplorer.utils

sealed class AnimeUIState<out T> {

    object Loading : AnimeUIState<Nothing>()

    data class Success<T>(
        val data: T
    ) : AnimeUIState<T>()

    data class Error(
        val message: String
    ) : AnimeUIState<Nothing>()
}