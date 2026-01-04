package com.example.animeexplorer.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeImages(

    @SerialName("jpg")
    val jpg: AnimeImageUrls,

    @SerialName("webp")
    val webp: AnimeImageUrls
)

@Serializable
data class AnimeImageUrls(

    @SerialName("image_url")
    val imageUrl: String?,

    @SerialName("small_image_url")
    val smallImageUrl: String?,

    @SerialName("large_image_url")
    val largeImageUrl: String?
)

@Serializable
data class AnimeTrailer(

    @SerialName("youtube_id")
    val youtubeId: String?,

    @SerialName("url")
    val url: String?,

    @SerialName("embed_url")
    val embedUrl: String?
)