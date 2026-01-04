package com.example.animeexplorer.domain.model

import com.example.animeexplorer.data.remote.dto.AnimeGenre

data class AnimeDetail(
    val id: Int,
    val title: String,
    val synopsis: String?,
    val posterUrl: String?,
    val trailerUrl: String?,
    val score: Double?,
    val type: String? = null,
    val episodes: Int? = null,
    val scoredBy: Int? = null,
    val genres: List<AnimeGenre> = emptyList()
)
