package com.traction.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.traction.core.data.database.entity.LocalMovie

@Dao
interface LocalMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(localMovie: LocalMovie)

    @Query("SELECT * FROM movie WHERE category = :category")
    suspend fun getMovieListByCategory(category: String): List<LocalMovie>


}
