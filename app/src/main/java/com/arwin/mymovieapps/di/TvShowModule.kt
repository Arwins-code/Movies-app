package com.arwin.mymovieapps.di

import com.arwin.mymovieapps.data.remote.TvShowTMDBApi
import com.arwin.mymovieapps.data.repository.tvshow.TvShowDetailsRepository
import com.arwin.mymovieapps.data.repository.tvshow.TvShowsGenresRepository
import com.arwin.mymovieapps.data.repository.tvshow.TvShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TvShowModule {
    @Singleton
    @Provides
    fun provideTvShowsDetailsRepository(tvShowApi: TvShowTMDBApi) =
        TvShowDetailsRepository(tvShowApi)

    @Singleton
    @Provides
    fun provideTvShowsGenresRepository(tvShowApi: TvShowTMDBApi) =
        TvShowsGenresRepository(tvShowApi)

    @Singleton
    @Provides
    fun provideTvShowsRepository(tvShowApi: TvShowTMDBApi) = TvShowsRepository(tvShowApi)
}