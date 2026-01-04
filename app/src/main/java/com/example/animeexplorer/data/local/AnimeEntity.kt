package com.example.animeexplorer.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val synopsis: String?,
    val posterUrl: String?,
    val trailerUrl: String?,
    val score: Double?,
    val type: String? = null,
    val episodes: Int? = null,
    val scoredBy: Int? = null,
    val genres: String ?= null
)