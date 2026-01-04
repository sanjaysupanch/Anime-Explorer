package com.example.animeexplorer.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.animeexplorer.data.local.AppDatabase
import com.example.animeexplorer.data.mapper.toAnime
import com.example.animeexplorer.data.mapper.toAnimeDetail
import com.example.animeexplorer.data.mapper.toDomain
import com.example.animeexplorer.data.network.NetworkMonitor
import com.example.animeexplorer.data.paging.AnimeRemoteMediator
import com.example.animeexplorer.data.remote.api.JikanApiService
import com.example.animeexplorer.domain.model.Anime
import com.example.animeexplorer.domain.model.AnimeDetail
import com.example.animeexplorer.domain.repository.AnimeRepository
import com.example.animeexplorer.utils.AnimeUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AnimeRepositoryImpl(
    private val api: JikanApiService,
    private val db: AppDatabase,
    private val networkMonitor: NetworkMonitor
) : AnimeRepository {


    @OptIn(ExperimentalPagingApi::class)
    override fun getTopAnime(): Flow<PagingData<Anime>> =
        Pager(
            PagingConfig(pageSize = 25),
            remoteMediator = AnimeRemoteMediator(api, db),
            pagingSourceFactory = { db.animeDao().pagingSource() }
        ).flow.map { it.map { entity -> entity.toAnime() } }

    override suspend fun getAnimeDetail(id: Int): AnimeUIState<AnimeDetail> =
        try {
            val isOnline = networkMonitor.isOnline.first()

            if (isOnline) {
                val dto = api.getAnimeDetail(id).data
                db.animeDao().updateScoredBy(dto.malId, dto.scoredBy ?: 0)
                AnimeUIState.Success(dto.toDomain())
            } else {
                db.animeDao().getAnimeById(id)?.let { entity ->
                    AnimeUIState.Success(entity.toAnimeDetail())
                } ?: AnimeUIState.Error("Anime not found in cache")

            }
        } catch (e: Exception) {
            AnimeUIState.Error(e.message ?: "Something went wrong")
        }
}