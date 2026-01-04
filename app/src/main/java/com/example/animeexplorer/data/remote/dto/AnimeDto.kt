package com.example.animeexplorer.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeDto(

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
    val titleEnglish: String?,

    @SerialName("type")
    val type: String?,

    @SerialName("episodes")
    val episodes: Int?,

    @SerialName("status")
    val status: String?,

    @SerialName("duration")
    val duration: String?,

    @SerialName("rating")
    val rating: String?,

    @SerialName("score")
    val score: Double?,

    @SerialName("rank")
    val rank: Int?,

    @SerialName("synopsis")
    val synopsis: String?,


    @SerialName("genres")
    val genres: List<AnimeGenre>
)