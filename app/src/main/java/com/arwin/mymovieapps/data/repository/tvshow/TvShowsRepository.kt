package com.arwin.mymovieapps.data.repository.tvshow

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.arwin.mymovieapps.data.remote.TvShowTMDBApi
import com.arwin.mymovieapps.data.paging.tvshow.AiringTodayTvShowsSource
import com.arwin.mymovieapps.data.paging.tvshow.OnTheAirTvShowsSource
import com.arwin.mymovieapps.data.paging.tvshow.PopularTvShowsSource
import com.arwin.mymovieapps.data.paging.tvshow.TopRatedTvShowsSource
import com.arwin.mymovieapps.data.paging.tvshow.TrendingTvShowsSource
import javax.inject.Inject

class TvShowsRepository @Inject constructor(private val tvShowApi: TvShowTMDBApi) {
    fun getAiringTodayTvShows() = Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                AiringTodayTvShowsSource(tvShowApi)
            }
        ).flow

    fun getOnTheAirTvShows() = Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                OnTheAirTvShowsSource(tvShowApi)
            }
        ).flow

    fun getPopularTvShows() = Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                PopularTvShowsSource(tvShowApi)
            }
        ).flow

    fun getTopRatedTvShows() = Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                TopRatedTvShowsSource(tvShowApi)
            }
        ).flow

    fun getTrendingThisWeekTvShows() = Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 27),
            pagingSourceFactory = {
                TrendingTvShowsSource(tvShowApi)
            }
        ).flow
}