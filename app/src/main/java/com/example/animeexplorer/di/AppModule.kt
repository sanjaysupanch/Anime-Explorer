package com.example.animeexplorer.di

import android.content.Context
import androidx.room.Room
import com.example.animeexplorer.data.local.AppDatabase
import com.example.animeexplorer.data.network.NetworkMonitor
import com.example.animeexplorer.data.remote.api.JikanApiService
import com.example.animeexplorer.data.repository.AnimeRepositoryImpl
import com.example.animeexplorer.domain.repository.AnimeRepository
import com.example.animeexplorer.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): JikanApiService {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
        val contentType = "application/json".toMediaType()
        
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(JikanApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "anime.db")
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    fun provideAnimeDao(db: AppDatabase) = db.animeDao()

    @Provides
    fun provideRemoteKeysDao(db: AppDatabase) = db.remoteKeysDao()

    @Provides
    fun provideRepository(
        api: JikanApiService,
        db: AppDatabase,
        monitor: NetworkMonitor
    ): AnimeRepository = AnimeRepositoryImpl(api, db, monitor)
}
