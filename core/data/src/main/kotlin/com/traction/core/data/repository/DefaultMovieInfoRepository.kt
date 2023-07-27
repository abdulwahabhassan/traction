package com.traction.core.data.repository

import com.traction.core.data.util.NetworkMonitor
import com.traction.core.common.di.IODispatcher
import com.traction.core.common.model.Resource
import com.traction.core.common.model.Result
import com.traction.core.data.datasource.local.LocalDataSource
import com.traction.core.data.datasource.remote.RemoteDataSource
import com.traction.core.data.util.asLocalMovieInfo
import com.traction.core.data.util.asMovieInfo
import com.traction.core.data.util.handleRequest
import com.traction.core.domain.repository.MovieInfoRepository
import com.traction.core.model.MovieInfo
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class DefaultMovieInfoRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val networkMonitor: NetworkMonitor,
    private val json: Json,
    private val remoteDatasource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : MovieInfoRepository {
    override suspend fun getMovieInfo(id: Long): Flow<Resource<MovieInfo>> = flow {
        emit(Resource.Loading)
        when (val result = handleRequest(
            dispatcher = ioDispatcher,
            networkMonitor = networkMonitor,
            json = json,
            apiRequest = { remoteDatasource.fetchMovieInfo(id) }
        )) {
            is Result.Error -> {
                val localResult = localDataSource.fetchMovieInfo(id)
                if (localResult == null) {
                    emit(Resource.Failed(result.message))
                } else {
                    emit(Resource.Ready(localResult.asMovieInfo()))
                }
            }

            is Result.Success -> emit(Resource.Ready(result.data.asMovieInfo()))
        }
    }

    override suspend fun insertMovieInfo(movieInfo: MovieInfo) {
        localDataSource.insertDetails(movieInfo.asLocalMovieInfo())
    }
}