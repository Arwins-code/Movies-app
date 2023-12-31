package com.arwin.mymovieapps.screens.home.filmdetails

import androidx.lifecycle.ViewModel
import com.arwin.mymovieapps.data.remote.responses.CreditResponse
import com.arwin.mymovieapps.data.remote.responses.movieresponses.MovieDetails
import com.arwin.mymovieapps.data.remote.responses.tvshowresponses.TvShowDetails
import com.arwin.mymovieapps.data.repository.movie.MovieDetailsRepository
import com.arwin.mymovieapps.data.repository.tvshow.TvShowDetailsRepository
import com.arwin.mymovieapps.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FilmDetailsViewModel @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val tvShowDetailsRepository: TvShowDetailsRepository
) : ViewModel() {
    suspend fun getMovieDetails(movieId: Int): Resource<MovieDetails> {
        return movieDetailsRepository.getMoviesDetails(movieId)
    }

    suspend fun getTvShowDetails(tvId: Int): Resource<TvShowDetails> {
        return tvShowDetailsRepository.getTvShowsDetails(tvId)
    }

    suspend fun getMovieCasts(movieId: Int): Resource<CreditResponse> {
        val cast = movieDetailsRepository.getMovieCasts(movieId)
        Timber.d(cast.data.toString())
        return cast
    }

    suspend fun getTvShowCasts(tvId: Int): Resource<CreditResponse> {
        return tvShowDetailsRepository.getTvShowsCasts(tvId)
    }
}