package com.arwin.mymovieapps.screens.home.filmdetails.common

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.arwin.mymovieapps.R
import com.arwin.mymovieapps.data.local.Favorite
import com.arwin.mymovieapps.screens.favorites.FavoritesViewModel
import com.arwin.mymovieapps.ui.theme.AppBarCollapsedHeight
import com.arwin.mymovieapps.ui.theme.AppBarExpendedHeight
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.math.max
import kotlin.math.min

@Composable
fun FilmImageBanner(
    scrollState: LazyListState,
    posterUrl: String,
    filmName: String,
    filmId: Int,
    filmType: String,
    releaseDate: String,
    rating: Float,
    navigator: DestinationsNavigator,
    viewModel: FavoritesViewModel
) {

    val context = LocalContext.current

    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight

    val maxOffset = with(LocalDensity.current) {
        imageHeight.roundToPx()
    } - WindowInsets.systemBars.getTop(density = Density(context))

    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)

    val offsetProgress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset

    SmallTopAppBar(
        modifier = Modifier
            .height(
                AppBarExpendedHeight
            )
            .offset { IntOffset(x = 0, y = -offset) },
        title = {
        },
        actions = {
            Column {
                Box{
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest.Builder(LocalContext.current).data(data = posterUrl)
                                .apply(block = fun ImageRequest.Builder.() {
                                    placeholder(R.drawable.placeholder)
                                    crossfade(true)
                                }).build()
                        ),
                        modifier = Modifier
                            .fillMaxSize()
                            .height(imageHeight)
                            .graphicsLayer {
                                alpha = 1f - offsetProgress
                            },
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
                    FilmNameAndRating(
                        filmName = filmName,
                        rating = rating
                    )
                }
            }
        }
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 10.dp)
    ) {
        CircularBackButtons(
            onClick = {
                navigator.popBackStack()
            }
        )
        CircularFavoriteButtons(
            isLiked = viewModel.isAFavorite(filmId).observeAsState().value != null,
            onClick = { isFav ->
                if (isFav) {
                    Toast.makeText(context, "Already added to your favorites", Toast.LENGTH_SHORT)
                        .show()
                    return@CircularFavoriteButtons
                } else {
                    viewModel.insertFavorite(
                        Favorite(
                            favorite = true,
                            mediaId = filmId,
                            mediaType = filmType,
                            image = posterUrl,
                            title = filmName,
                            releaseDate = releaseDate,
                            rating = rating
                        )
                    )
                }
            }
        )
    }
}