package com.traction.core.data.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.traction.core.data.database.entity.GenreEntity
import com.traction.core.data.database.entity.ProductionCompanyEntity
import com.traction.core.data.database.entity.SpokenLanguageEntity
import javax.inject.Inject
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ProvidedTypeConverter
class Converter @Inject constructor (
    private val json: Json
) {
    //Local Genre Converter
    @TypeConverter
    fun fromListOfGenreEntity(value: List<GenreEntity>?): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toListOfGenreEntity(value: String?): List<GenreEntity>? {
        return value?.let { json.decodeFromString(it) }
    }

    //Local Production Company Converter
    @TypeConverter
    fun fromListOfProductionCompanyEntity(value: List<ProductionCompanyEntity>?): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toListOfProductionCompanyEntity(value: String?): List<ProductionCompanyEntity>? {
        return value?.let { json.decodeFromString(it) }
    }

    //Local Spoken Languages Converter
    @TypeConverter
    fun fromListOfSpokenLanguagesEntity(value: List<SpokenLanguageEntity>?): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toListOfSpokenLanguagesEntity(value: String?): List<SpokenLanguageEntity>? {
        return value?.let { json.decodeFromString(it) }
    }
}