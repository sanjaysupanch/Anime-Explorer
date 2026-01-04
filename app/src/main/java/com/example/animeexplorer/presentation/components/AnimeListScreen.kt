package com.example.animeexplorer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animeexplorer.presentation.viewmodel.AnimeViewModel
import com.example.animeexplorer.ui.theme.IMDbBlack
import com.example.animeexplorer.ui.theme.IMDbDarkGray
import com.example.animeexplorer.ui.theme.IMDbYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen(
    vm: AnimeViewModel = hiltViewModel(),
    onClick: (Int) -> Unit
) {
    val anime = vm.animePaging.collectAsLazyPagingItems()

    Scaffold(
        containerColor = IMDbBlack,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Anime Explorer",
                        fontWeight = FontWeight.Bold,
                        color = IMDbYellow
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = IMDbDarkGray,
                    scrolledContainerColor = IMDbDarkGray
                )
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .background(IMDbBlack)
        ) {
            items(
                count = anime.itemCount,
                key = { index -> anime[index]?.id ?: index }
            ) { index ->
                val item = anime[index]
                if (item != null) {
                    AnimeListItem(
                        anime = item,
                        onClick = { onClick(item.id) }
                    )
                }
            }
        }
    }
}
