package com.example.animeexplorer.data.mapper

import com.example.animeexplorer.data.local.AnimeEntity
import com.example.animeexplorer.data.local.converter.GenreConverter.toGenreList
import com.example.animeexplorer.data.remote.dto.AnimeDetailDto
import com.example.animeexplorer.domain.model.Anime
import com.example.animeexplorer.domain.model.AnimeDetail

fun AnimeDetailDto.toDomain() =
    AnimeDetail(
        id = malId,
        title = title,
        synopsis = synopsis,
        posterUrl = images.webp.imageUrl,
        trailerUrl = trailer.embedUrl,
        score = score,
        type = type,
        episodes = episodes,
        scoredBy = scoredBy,
        genres = genres
    )


fun AnimeEntity.toAnime() : Anime =
    Anime(
        id,
        title,
        posterUrl,
        score,
        episodes
    )

fun AnimeEntity.toAnimeDetail(): AnimeDetail =
    AnimeDetail(
        id,
        title,
        synopsis,
        posterUrl,
        trailerUrl,
        score,
        type,
        episodes,
        scoredBy,
        genres = genres?.let { toGenreList(it) } ?: emptyList()
    )