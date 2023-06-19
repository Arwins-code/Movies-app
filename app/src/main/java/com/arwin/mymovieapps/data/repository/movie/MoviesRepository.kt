package com.arwin.mymovieapps.data.repository.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.arwin.mymovieapps.data.remote.MovieTMDBApi
import com.arwin.mymovieapps.data.paging.movie.NowPlayingMoviesSource
import com.arwin.mymovieapps.data.paging.movie.PopularMoviesSource
import com.arwin.mymovieapps.data.paging.movie.TopRatedMoviesSource
import com.arwin.mymovieapps.data.paging.movie.TrendingMoviesSource
import com.arwin.mymovieapps.data.paging.movie.UpcomingMoviesSource
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val movieApi: MovieTMDBApi) {
    fun getTrendingMoviesThisWeek() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = {
            TrendingMoviesSource(movieApi)
        }
    ).flow

    fun getUpcomingMovies() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = {
            UpcomingMoviesSource(movieApi)
        }
    ).flow


    fun getTopRatedMovies() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = {
            TopRatedMoviesSource(movieApi)
        }
    ).flow

    fun getNowPlayingMovies() = Pager(
        pagingSourceFactory = {
            NowPlayingMoviesSource(movieApi)
        },
        config = PagingConfig(pageSize = 20, enablePlaceholders = false)
    ).flow

    fun getPopularMovies() = Pager(
        config = PagingConfig(enablePlaceholders = false, pageSize = 20),
        pagingSourceFactory = {
            PopularMoviesSource(movieApi)
        }
    ).flow
}