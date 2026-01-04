package com.example.animeexplorer.data.local.converter

import androidx.room.TypeConverter
import com.example.animeexplorer.data.remote.dto.AnimeGenre
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object GenreConverter {

    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromGenreList(genres: List<AnimeGenre>): String {
        return json.encodeToString(genres)
    }

    @TypeConverter
    fun toGenreList(genresJson: String): List<AnimeGenre> {
        return json.decodeFromString(genresJson)
    }
}