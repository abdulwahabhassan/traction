package com.traction.core.data.datasource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.traction.core.network.Constants
import com.devhassan.data.api.MoviesApiService
import com.traction.core.data.datasource.MoviesDataSrc
import com.traction.core.data.datasource.paging.MoviesPagingSource
import com.traction.core.network.model.RemoteMovieInfo
import com.traction.core.network.model.RemoteMovie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: MoviesApiService
) : MoviesDataSrc<Flow<PagingData<RemoteMovie>>, RemoteMovieInfo> {

    override suspend fun fetchMovieList(category: String): Flow<PagingData<RemoteMovie>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviesPagingSource(apiService, category) }
        ).flow
    }

    override suspend fun fetchMovieInfo(id: Long): RemoteMovieInfo {
        return apiService.getMovieInfoById(id)
    }


}