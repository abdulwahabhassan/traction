package com.traction.core.domain.repository

import androidx.paging.PagingData
import com.traction.core.common.model.Resource
import com.traction.core.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun getMovieList(
        category: String
    ): Resource<Flow<PagingData<Movie>>>

    suspend fun insertMovie(
        movie: Movie
    )

}