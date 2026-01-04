package com.example.animeexplorer.domain.model

data class Anime(
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val score: Double?,
    val episodes: Int? = null
)

