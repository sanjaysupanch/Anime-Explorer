package com.example.animeexplorer.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.animeexplorer.data.local.AnimeEntity
import com.example.animeexplorer.data.local.AppDatabase
import com.example.animeexplorer.data.local.RemoteKeys
import com.example.animeexplorer.data.local.converter.GenreConverter.fromGenreList
import com.example.animeexplorer.data.remote.api.JikanApiService

@OptIn(ExperimentalPagingApi::class)
class AnimeRemoteMediator(
    private val api: JikanApiService,
    private val db: AppDatabase
) : RemoteMediator<Int, AnimeEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeEntity>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.APPEND -> {
                val last = state.lastItemOrNull() ?: return MediatorResult.Success(true)
                db.remoteKeysDao().remoteKeysById(last.id)?.nextPage
                    ?: return MediatorResult.Success(true)
            }
            LoadType.PREPEND -> return MediatorResult.Success(true)
        }

        return try {
            val response = api.getTopAnime(page).data

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.animeDao().clear()
                    db.remoteKeysDao().clearAll()
                }

                db.remoteKeysDao().insertAll(
                    response.map { RemoteKeys(it.malId, page + 1) }
                )

                db.animeDao().insertAll(
                    response.map {
                        AnimeEntity(
                            id = it.malId,
                            title = it.title,
                            synopsis = it.synopsis,
                            posterUrl = it.images.webp.imageUrl,
                            trailerUrl = it.trailer.embedUrl,
                            score = it.score,
                            type = it.type,
                            episodes = it.episodes,
                            scoredBy = null,
                            genres = fromGenreList(it.genres)
                        )
                    }
                )
            }

            MediatorResult.Success(response.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}