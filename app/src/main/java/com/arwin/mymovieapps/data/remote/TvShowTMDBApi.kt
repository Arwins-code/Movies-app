package com.arwin.mymovieapps.data.remote

import com.arwin.mymovieapps.BuildConfig.API_KEY
import com.arwin.mymovieapps.data.remote.responses.CreditResponse
import com.arwin.mymovieapps.data.remote.responses.GenresResponse
import com.arwin.mymovieapps.data.remote.responses.tvshowresponses.TVShowResponse
import com.arwin.mymovieapps.data.remote.responses.tvshowresponses.TvShowDetails
import com.arwin.mymovieapps.util.Constant.LANGUAGE_EN
import com.arwin.mymovieapps.util.Constant.STARTING_PAGE_INDEX
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowTMDBApi {
    @GET("tv/{tv_id}")
    suspend fun getTvShowDetails(
        @Path("tv_id") tvSeriesId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): TvShowDetails

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): TVShowResponse

    @GET("trending/tv/day")
    suspend fun getTrendingTvShows(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): TVShowResponse

    @GET("tv/on_the_air")
    suspend fun getOnTheAirTvShows(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): TVShowResponse

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): TVShowResponse

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvShows(
        @Query("page") page: Int = STARTING_PAGE_INDEX,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): TVShowResponse

    @GET("tv/{tv_id}/credits")
    suspend fun getTvShowCredits(
        @Path("tv_id") tvSeriesId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): CreditResponse

    @GET("genre/tv/list")
    suspend fun getTvShowGenres(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE_EN
    ): GenresResponse
}