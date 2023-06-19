package com.arwin.mymovieapps.di

import com.arwin.mymovieapps.data.remote.SearchTMDBApi
import com.arwin.mymovieapps.data.repository.MultiSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MultiSearchModule {
    @Singleton
    @Provides
    fun provideMultiSearchRepository(searchApi: SearchTMDBApi) = MultiSearchRepository(searchApi)
}