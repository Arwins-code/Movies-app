package com.arwin.mymovieapps.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.arwin.mymovieapps.data.paging.SearchPagingSource
import com.arwin.mymovieapps.data.remote.SearchTMDBApi
import javax.inject.Inject

class MultiSearchRepository @Inject constructor(private val searchApi: SearchTMDBApi) {
    fun multiSearch(queryParam: String) = Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 1),
            pagingSourceFactory = {
                SearchPagingSource(searchApi, queryParam)
            }
        ).flow
}