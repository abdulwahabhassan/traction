package com.traction.core.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.traction.core.data.database.converter.Converter
import com.traction.core.data.database.dao.LocalMovieDao
import com.traction.core.data.database.dao.LocalMovieInfoDao
import com.traction.core.data.database.entity.LocalMovie
import com.traction.core.data.database.entity.LocalMovieInfo
import kotlinx.serialization.json.Json

@Database(
    entities = [LocalMovie::class, LocalMovieInfo::class],
    version = 1,
    exportSchema = false
)
public abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun localMovieDao(): LocalMovieDao

    abstract fun localMovieInfoDao(): LocalMovieInfoDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context, json: Json): AppRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppRoomDatabase::class.java, "app_database"
                ).addTypeConverter(
                    Converter(json)
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}