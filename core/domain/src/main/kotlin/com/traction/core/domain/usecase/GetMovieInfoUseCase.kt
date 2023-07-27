package com.traction.core.domain.usecase

import com.traction.core.common.model.Resource
import com.traction.core.domain.repository.MovieInfoRepository
import com.traction.core.model.MovieInfo
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetMovieInfoUseCase  @Inject constructor(
    private val movieInfoRepository: MovieInfoRepository,
) {
    suspend operator fun invoke(id: Long): Flow<Resource<MovieInfo>> =
        movieInfoRepository.getMovieInfo(id)
}