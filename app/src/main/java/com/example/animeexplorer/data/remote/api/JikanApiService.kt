package com.example.animeexplorer.data.remote.api

import com.example.animeexplorer.data.remote.dto.AnimeDetailDto
import com.example.animeexplorer.data.remote.dto.AnimeDto
import com.example.animeexplorer.data.remote.dto.JikanResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JikanApiService {

    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int
    ): JikanResponse<List<AnimeDto>>

    @GET("anime/{id}")
    suspend fun getAnimeDetail(
        @Path("id") id: Int
    ): JikanResponse<AnimeDetailDto>
}