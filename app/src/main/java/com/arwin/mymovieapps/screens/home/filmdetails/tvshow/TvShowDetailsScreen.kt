package com.arwin.mymovieapps.screens.home.filmdetails.tvshow

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.arwin.mymovieapps.screens.home.filmdetails.common.FilmInfo
import com.arwin.mymovieapps.screens.home.filmdetails.common.ImageSection
import com.arwin.mymovieapps.data.remote.responses.CreditResponse
import com.arwin.mymovieapps.data.remote.responses.tvshowresponses.TvShowDetails
import com.arwin.mymovieapps.screens.favorites.FavoritesViewModel
import com.arwin.mymovieapps.screens.home.filmdetails.FilmDetailsViewModel
import com.arwin.mymovieapps.util.Constant
import com.arwin.mymovieapps.util.Resource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TvShowDetailsScreen(
    filmId: Int,
    navigator: DestinationsNavigator,
    viewModel: FilmDetailsViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val scrollState = rememberLazyListState()

    val details = produceState<Resource<TvShowDetails>>(initialValue = Resource.Loading()) {
        value = viewModel.getTvShowDetails(filmId)
    }.value

    val casts = produceState<Resource<CreditResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getTvShowCasts(filmId)
    }.value

    LazyColumn() {
        item {
            if (details is Resource.Success) {

                Box {
                    ImageSection(
                        posterUrl = "${Constant.IMAGE_BASE_URL}/${details.data?.backdropPath}",
                        filmName = details.data?.name.toString(),
                        filmId = details.data?.id!!,
                        filmType = "movie",
                        releaseDate = details.data.firstAirDate,
                        rating = details.data.voteAverage?.toFloat()!!,
                        navigator = navigator,
                        viewModel = favoritesViewModel
                    )
                }
                FilmInfo(
                    scrollState = scrollState,
                    overview = details.data?.overview.toString(),
                    releaseDate = details.data?.firstAirDate.toString(),
                    navigator = navigator,
                    casts = casts
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

        }
    }
}