package com.traction.core.domain.usecase

import androidx.paging.PagingData
import com.traction.core.common.model.Resource
import com.traction.core.domain.repository.MovieListRepository
import com.traction.core.model.Movie
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMovieListUseCase  @Inject constructor(
    private val movieInfoRepository: MovieListRepository,
) {
    suspend operator fun invoke(category: String): Resource<Flow<PagingData<Movie>>> =
        movieInfoRepository.getMovieList(category)
}