package com.arwin.mymovieapps.data.paging.tvshow

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arwin.mymovieapps.data.remote.TvShowTMDBApi
import com.arwin.mymovieapps.model.tvshow.TvShow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AiringTodayTvShowsSource @Inject constructor(private val api: TvShowTMDBApi) :
    PagingSource<Int, TvShow>() {
    override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
        return try {
            val nextPage = params.key ?: 1
            val airingTvShowsToday = api.getAiringTodayTvShows(nextPage)
            LoadResult.Page(
                data = airingTvShowsToday.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (airingTvShowsToday.results.isEmpty()) null else airingTvShowsToday.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}