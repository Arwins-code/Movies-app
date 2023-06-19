package com.arwin.mymovieapps.data.remote

import com.arwin.mymovieapps.BuildConfig.API_KEY
import com.arwin.mymovieapps.data.remote.responses.MultiSearchResponse
import com.arwin.mymovieapps.util.Constant.STARTING_PAGE_INDEX
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchTMDBApi {
    @GET("search/multi")
    suspend fun multiSearch(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("query") query: String,
        @Query("api_key") apiKey: String = API_KEY
    ): MultiSearchResponse
}