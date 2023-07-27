package com.traction.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.traction.core.data.util.NetworkMonitor
import com.traction.core.common.di.IODispatcher
import com.traction.core.common.model.Resource
import com.traction.core.common.model.Result
import com.traction.core.data.datasource.local.LocalDataSource
import com.traction.core.data.datasource.remote.RemoteDataSource
import com.traction.core.data.util.asLocalMovie
import com.traction.core.data.util.asMovie
import com.traction.core.data.util.handleRequest
import com.traction.core.domain.repository.MovieListRepository
import com.traction.core.model.Movie
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class DefaultMovieListRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val networkMonitor: NetworkMonitor,
    private val json: Json,
    private val remoteDatasource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : MovieListRepository {
    override suspend fun getMovieList(category: String): Resource<Flow<PagingData<Movie>>> =
        withContext(ioDispatcher) {
            when (val result = handleRequest(
                dispatcher = ioDispatcher,
                networkMonitor = networkMonitor,
                json = json,
                apiRequest = { remoteDatasource.fetchMovieList(category) }
            )) {
                is Result.Error -> {
                    Resource.Failed(result.message)
                }

                is Result.Success -> {
                    Resource.Ready(result.data.map { pagingData ->
                        pagingData.map { remoteMovie -> remoteMovie.asMovie() }
                    })
                }
            }
        }

    override suspend fun insertMovie(movie: Movie) {
        localDataSource.insertMovie(movie.asLocalMovie())
    }
}

