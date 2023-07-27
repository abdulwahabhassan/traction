package com.devhassan.data.api

import com.traction.core.network.model.RemoteMovieInfo
import com.traction.core.network.model.RemoteMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET(value = "movie/{category}")
    suspend fun getMovies(
        @Path("category") category: String,
        @Query("page") page: Int,
    ): RemoteMoviesResponse

    @GET(value = "movie/{movie_id}")
    suspend fun getMovieInfoById(
        @Path("movie_id") movieId: Long,
    ): RemoteMovieInfo

}
