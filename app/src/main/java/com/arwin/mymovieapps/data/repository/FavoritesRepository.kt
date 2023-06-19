package com.arwin.mymovieapps.data.repository

import androidx.lifecycle.LiveData
import com.arwin.mymovieapps.data.local.Favorite
import com.arwin.mymovieapps.data.local.FavoritesDatabase
import javax.inject.Inject

class FavoritesRepository @Inject constructor(private val database: FavoritesDatabase) {
    suspend fun insertFavorite(favorite: Favorite) {
        database.dao.insertFavorite(favorite)
    }

    fun getFavorite(): LiveData<List<Favorite>> {
        return database.dao.getAllFavorites()
    }

    fun getAFavorite(mediaId: Int): LiveData<Favorite?> {
        return database.dao.getAFavorite(mediaId)
    }

    fun isFavorite(mediaId: Int): LiveData<Boolean> {
        return database.dao.isFavorite(mediaId)
    }

    suspend fun deleteOneFavorite(favorite: Favorite) {
        database.dao.deleteAFavorite(favorite)
    }

    suspend fun deleteAllFavorites() {
        database.dao.deleteAllFavorites()
    }
}