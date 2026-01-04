package com.example.animeexplorer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.animeexplorer.R
import com.example.animeexplorer.presentation.viewmodel.AnimeViewModel
import com.example.animeexplorer.ui.theme.IMDbBlack
import com.example.animeexplorer.ui.theme.IMDbDarkGray
import com.example.animeexplorer.ui.theme.IMDbLightGray
import com.example.animeexplorer.ui.theme.IMDbWhite
import com.example.animeexplorer.ui.theme.IMDbYellow
import com.example.animeexplorer.utils.AnimeUIState

@Composable
fun AnimeDetailScreen(
    id: Int,
    vm: AnimeViewModel = hiltViewModel()
) {
    LaunchedEffect(id) { vm.load(id) }
    val isOnlineState = vm.isOnline.collectAsState(initial = true)
    val isOnline = isOnlineState.value

    val scrollState = rememberScrollState()

    Scaffold(containerColor = IMDbBlack) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            when (val state = vm.state) {

                is AnimeUIState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = IMDbYellow
                    )
                }

                is AnimeUIState.Error -> {
                    Text(
                        text = state.message,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is AnimeUIState.Success -> {
                    val data = state.data

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(scrollState)
                    ) {
                        val trailerUrl = data.trailerUrl
                        val posterUrl = data.posterUrl

                        if (!trailerUrl.isNullOrEmpty() && isOnline) {
                            TrailerPlayer(trailerUrl)
                        } else {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(posterUrl)
                                    .crossfade(true)
                                    .error(R.drawable.ic_placeholder)
                                    .placeholder(R.drawable.ic_placeholder)
                                    .build(),
                                contentDescription = "Anime Poster",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(16f / 9f)
                                    .background(IMDbDarkGray)
                            )
                        }

                        Column(modifier = Modifier.padding(16.dp)) {

                            Text(
                                text = data.title,
                                style = MaterialTheme.typography.headlineMedium,
                                color = IMDbWhite
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = data.type ?: "TV Series",
                                    color = IMDbLightGray,
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("•", color = IMDbLightGray)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${data.episodes ?: "?"} eps",
                                    color = IMDbLightGray,
                                    fontSize = 12.sp
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Icon(
                                    Icons.Rounded.Star,
                                    contentDescription = null,
                                    tint = IMDbYellow,
                                    modifier = Modifier.size(24.dp)
                                )

                                Spacer(modifier = Modifier.width(6.dp))

                                Column {
                                    Text(
                                        text = "${data.score ?: "N/A"}/10",
                                        color = IMDbWhite,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "${data.scoredBy ?: 0} votes",
                                        color = IMDbLightGray,
                                        fontSize = 10.sp
                                    )
                                }

                                Spacer(modifier = Modifier.weight(1f))

                                Button(
                                    onClick = { /* TODO */ },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = IMDbYellow
                                    ),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Icon(Icons.Default.Add, null, tint = IMDbBlack)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        "Watchlist",
                                        color = IMDbBlack,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            HorizontalDivider(
                                color = IMDbDarkGray,
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 16.dp)
                            )

                            Row {
                                data.genres.forEach { genre ->
                                    Box(
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .border(
                                                1.dp,
                                                IMDbLightGray,
                                                RoundedCornerShape(16.dp)
                                            )
                                            .padding(
                                                horizontal = 12.dp,
                                                vertical = 6.dp
                                            )
                                    ) {
                                        Text(
                                            text = genre.name,
                                            color = IMDbWhite,
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(
                                text = "Plot",
                                color = IMDbYellow,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = data.synopsis ?: "No synopsis available.",
                                color = IMDbWhite,
                                fontSize = 14.sp,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}