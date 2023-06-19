package com.arwin.mymovieapps.data.repository.tvshow

import com.arwin.mymovieapps.data.remote.TvShowTMDBApi
import com.arwin.mymovieapps.data.remote.responses.GenresResponse
import com.arwin.mymovieapps.util.Resource
import timber.log.Timber
import javax.inject.Inject

class TvShowsGenresRepository @Inject constructor(private val tvShowApi: TvShowTMDBApi) {
    suspend fun getTvShowsGenres(): Resource<GenresResponse> {
        val response = try {
            tvShowApi.getTvShowGenres()
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Tv Shows genres; $response")
        return Resource.Success(response)
    }
}