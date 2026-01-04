package com.example.animeexplorer.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeGenre(

    @SerialName("mal_id")
    val malId: Int,

    @SerialName("type")
    val type: String,

    @SerialName("name")
    val name: String,

    @SerialName("url")
    val url: String
)