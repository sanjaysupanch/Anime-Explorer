package com.example.animeexplorer.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AnimeDao {
    @Query("SELECT * FROM anime")
    fun pagingSource(): PagingSource<Int, AnimeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<AnimeEntity>)

    @Query("UPDATE anime SET scoredBy = :count WHERE id = :id")
    suspend fun updateScoredBy(id: Int, count: Int?)

    @Query("SELECT * FROM anime WHERE id = :id")
    suspend fun getAnimeById(id: Int): AnimeEntity?

    @Query("DELETE FROM anime")
    suspend fun clear()
}