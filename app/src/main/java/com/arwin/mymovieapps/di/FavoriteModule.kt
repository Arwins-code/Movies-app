package com.arwin.mymovieapps.di

import android.app.Application
import androidx.room.Room
import com.arwin.mymovieapps.data.local.FavoritesDatabase
import com.arwin.mymovieapps.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteModule {
    @Singleton
    @Provides
    fun provideFavoriteDatabase(application: Application): FavoritesDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavoritesDatabase::class.java,
            Constant.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }


}