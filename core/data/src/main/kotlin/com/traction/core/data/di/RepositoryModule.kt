package com.traction.core.data.di

import com.traction.core.data.repository.DefaultMovieInfoRepository
import com.traction.core.data.repository.DefaultMovieListRepository
import com.traction.core.domain.repository.MovieInfoRepository
import com.traction.core.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsMovieListRepository(
        repository: DefaultMovieListRepository
    ): MovieListRepository

    @Binds
    fun bindsMovieInfoRepository(
        repository: DefaultMovieInfoRepository
    ): MovieInfoRepository
}