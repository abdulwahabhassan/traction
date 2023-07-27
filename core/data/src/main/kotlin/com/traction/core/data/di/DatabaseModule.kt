package com.traction.core.data.di

import android.content.Context
import com.traction.core.data.database.AppRoomDatabase
import com.traction.core.data.database.dao.LocalMovieInfoDao
import com.traction.core.data.database.dao.LocalMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext appContext: Context,
        json: Json
    ): AppRoomDatabase {
        return AppRoomDatabase.getDatabase(appContext, json)
    }

    @Provides
    @Singleton
    fun providesMovieEntityDao(
        database: AppRoomDatabase
    ): LocalMovieDao {
        return database.localMovieDao()
    }

    @Provides
    @Singleton
    fun providesDetailsEntityDao(
        database: AppRoomDatabase
    ): LocalMovieInfoDao {
        return database.localMovieInfoDao()
    }
}
