package com.traction.core.domain.repository

import com.traction.core.model.MovieInfo
import com.traction.core.common.model.Resource
import com.traction.core.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieInfoRepository {
    suspend fun getMovieInfo(
        id: Long
    ): Flow<Resource<MovieInfo>>

    suspend fun insertMovieInfo(
        movieInfo: MovieInfo
    )
}