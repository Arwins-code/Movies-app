package com.arwin.mymovieapps.screens.favorites

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.arwin.mymovieapps.screens.home.filmdetails.common.VoteAverageRatingIndicator
import com.arwin.mymovieapps.R
import com.arwin.mymovieapps.data.local.Favorite
import com.arwin.mymovieapps.screens.commons.MovplayToolbar
import com.arwin.mymovieapps.screens.destinations.MovieDetailsScreenDestination
import com.arwin.mymovieapps.screens.destinations.TvShowDetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Destination
@Composable
fun FavoritesScreen(
    navigator: DestinationsNavigator,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val openDialog = remember {
            mutableStateOf(false)
        }
        val favoriteFilms = viewModel.favorites.observeAsState(initial = emptyList())

        MovplayToolbar(
            navigator = navigator,
            title = {
                androidx.compose.material3.Text(text = "Favorites")
            },
            showBackArrow = false,
            navActions = {
                androidx.compose.material3.IconButton(onClick = {
                    openDialog.value = true
                }) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                    )
                }
            }
        )

        LazyColumn(modifier = Modifier.padding(horizontal = 8.dp)) {
            items(
                items = favoriteFilms.value,
                key = { favoriteFilms: Favorite -> favoriteFilms.mediaId }) { favorite ->
                val dismissState = rememberDismissState()

                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                    viewModel.deleteOneFavorite(favorite)
                }
                SwipeToDismiss(
                    state = dismissState,
                    modifier = Modifier
                        .padding(vertical = Dp(1f)),
                    directions = setOf(
                        DismissDirection.EndToStart
                    ),
                    dismissThresholds = { direction ->
                        FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.1f else 0.05f)
                    },
                    background = {
                        val color by animateColorAsState(
                            when (dismissState.targetValue) {
                                DismissValue.Default -> MaterialTheme.colorScheme.surface
                                else -> MaterialTheme.colorScheme.error
                            }
                        )
                        val alignment = Alignment.CenterEnd
                        val icon = Icons.Filled.Delete
                        val scale by animateFloatAsState(
                            if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                        )
                        Box(
                            Modifier
                                .fillMaxSize()
                                .clip(shape = MaterialTheme.shapes.medium)
                                .background(color)
                                .padding(horizontal = Dp(20f)),
                            contentAlignment = alignment
                        ) {
                            androidx.compose.material3.Icon(
                                icon,
                                contentDescription = "Delete Icon",
                                modifier = Modifier.scale(scale),
                                tint = MaterialTheme.colorScheme.onError
                            )
                        }
                    },
                    dismissContent = {
                        androidx.compose.material3.Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(230.dp)
                                .align(alignment = Alignment.CenterVertically)
                                .clickable {
                                    if (favorite.mediaType == "tv") {
                                        navigator.navigate(TvShowDetailsScreenDestination(favorite.mediaId))
                                    } else if (favorite.mediaType == "movie") {
                                        navigator.navigate(MovieDetailsScreenDestination(favorite.mediaId))
                                    }
                                }
                        ) {
                            FilmItem(
                                filmItem = favorite,
                            )
                        }
                    }
                )
            }
        }
        Timber.d(favoriteFilms.value.isEmpty().toString())

        if ((favoriteFilms.value.isEmpty())) {
            Timber.d("Empty")
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(250.dp),
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = null
                )
            }
        }

        if (openDialog.value) {
            androidx.compose.material3.AlertDialog(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    androidx.compose.material3.Text(text = "Delete all favorites")
                },
                text = {
                    androidx.compose.material3.Text(text = "Are you want to delete all?")
                },
                confirmButton = {
                    androidx.compose.material3.TextButton(
                        onClick = {
                            viewModel.deleteAllFavorites()
                            openDialog.value = false
                        },
                    ) {
                        androidx.compose.material3.Text("Yes")
                    }
                },
                dismissButton = {
                    androidx.compose.material3.TextButton(
                        onClick = {
                            openDialog.value = false
                        },
                    ) {
                        androidx.compose.material3.Text("No")
                    }
                },

                )
        }
    }
}

@Composable
fun FilmItem(
    filmItem: Favorite,
) {
    Box {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = filmItem.image)
                    .apply(block = fun ImageRequest.Builder.() {
                        placeholder(R.drawable.placeholder)
                        crossfade(true)
                    }).build()
            ),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            contentDescription = "Movie Banner"
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            Pair(0.3f, Transparent),
                            Pair(1.5f, Color.Black)
                        )
                    )
                )
        )

        FilmDetails(
            title = filmItem.title,
            releaseDate = filmItem.releaseDate,
            rating = filmItem.rating
        )
    }
}

@Composable
fun FilmDetails(
    title: String,
    releaseDate: String,
    rating: Float
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                androidx.compose.material3.Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                )
                androidx.compose.material3.Text(
                    text = releaseDate,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            VoteAverageRatingIndicator(
                percentage = rating
            )
        }
    }
}