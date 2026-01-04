package com.example.animeexplorer.domain.repository

import androidx.paging.PagingData
import com.example.animeexplorer.domain.model.Anime
import com.example.animeexplorer.domain.model.AnimeDetail
import com.example.animeexplorer.utils.AnimeUIState
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getTopAnime(): Flow<PagingData<Anime>>
    suspend fun getAnimeDetail(id: Int): AnimeUIState<AnimeDetail>
}