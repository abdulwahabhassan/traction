package com.traction.core.data.datasource.local

import com.traction.core.data.database.dao.LocalMovieInfoDao
import com.traction.core.data.database.dao.LocalMovieDao
import com.traction.core.data.database.entity.LocalMovie
import com.traction.core.data.database.entity.LocalMovieInfo
import com.traction.core.data.datasource.MoviesDataSrc
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val localMovieDao: LocalMovieDao,
    private val localMovieInfoDao: LocalMovieInfoDao
) : MoviesDataSrc<List<LocalMovie>, LocalMovieInfo> {

    override suspend fun fetchMovieList(category: String): List<LocalMovie> {
        return localMovieDao.getMovieListByCategory(category)
    }

    override suspend fun fetchMovieInfo(id: Long): LocalMovieInfo? {
        return localMovieInfoDao.getMovieInfoById(id)
    }

    suspend fun insertMovie(localLocalMovie: LocalMovie) {
        localMovieDao.insertMovie(localLocalMovie)
    }

    suspend fun insertDetails(localMovieInfo: LocalMovieInfo) {
        localMovieInfoDao.insertMovieInfo(localMovieInfo)
    }

}