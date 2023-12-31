package com.arwin.mymovieapps.data.paging.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arwin.mymovieapps.data.remote.MovieTMDBApi
import com.arwin.mymovieapps.model.movie.Movie
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TrendingMoviesSource @Inject constructor(private val api: MovieTMDBApi) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val trendingMovies = api.getTrendingTodayMovies(nextPage)
            LoadResult.Page(
                data = trendingMovies.searches,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (trendingMovies.searches.isEmpty()) null else trendingMovies.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}