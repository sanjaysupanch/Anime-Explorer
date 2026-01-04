package com.example.animeexplorer.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.animeexplorer.data.network.NetworkMonitor
import com.example.animeexplorer.domain.model.AnimeDetail
import com.example.animeexplorer.domain.repository.AnimeRepository
import com.example.animeexplorer.utils.AnimeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val repo: AnimeRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    val animePaging = repo.getTopAnime().cachedIn(viewModelScope)

    var state by mutableStateOf<AnimeUIState<AnimeDetail>>(AnimeUIState.Loading)
        private set

    val isOnline = networkMonitor.isOnline

    fun load(id: Int) = viewModelScope.launch {
        state = repo.getAnimeDetail(id)
    }
}