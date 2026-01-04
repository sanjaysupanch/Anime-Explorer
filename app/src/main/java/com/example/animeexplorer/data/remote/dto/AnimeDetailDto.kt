package com.example.animeexplorer.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeDetailDto(

    @SerialName("mal_id")
    val malId: Int,

    @SerialName("url")
    val url: String,

    @SerialName("images")
    val images: AnimeImages,

    @SerialName("trailer")
    val trailer: AnimeTrailer,

    @SerialName("title")
    val title: String,

    @SerialName("title_english")
    val titleEnglish: String? = null,

    @SerialName("title_japanese")
    val titleJapanese: String? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("source")
    val source: String? = null,

    @SerialName("episodes")
    val episodes: Int? = null,

    @SerialName("status")
    val status: String? = null,

    @SerialName("airing")
    val airing: Boolean? = null,

    @SerialName("duration")
    val duration: String? = null,

    @SerialName("rating")
    val rating: String? = null,

    @SerialName("score")
    val score: Double? = null,

    @SerialName("scored_by")
    val scoredBy: Int? = null,

    @SerialName("rank")
    val rank: Int? = null,

    @SerialName("popularity")
    val popularity: Int? = null,

    @SerialName("members")
    val members: Int? = null,

    @SerialName("favorites")
    val favorites: Int? = null,

    @SerialName("synopsis")
    val synopsis: String? = null,

    @SerialName("season")
    val season: String? = null,

    @SerialName("genres")
    val genres: List<AnimeGenre> = emptyList(),

)