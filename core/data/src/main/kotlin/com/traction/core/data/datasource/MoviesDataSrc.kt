package com.traction.core.data.datasource


interface MoviesDataSrc<T, K> {

    suspend fun fetchMovieList(category: String): T

    suspend fun fetchMovieInfo(id: Long): K?
}