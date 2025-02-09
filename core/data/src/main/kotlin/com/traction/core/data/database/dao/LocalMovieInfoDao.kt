package com.traction.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.traction.core.data.database.entity.LocalMovieInfo

@Dao
interface LocalMovieInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieInfo(localMovieInfo: LocalMovieInfo)

    @Query("SELECT * FROM movie_info WHERE id = :id")
    suspend fun getMovieInfoById(id: Long): LocalMovieInfo?
}
