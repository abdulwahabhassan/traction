package com.traction.core.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteMovieInfo(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String,

    @SerialName("belongs_to_collection")
    val belongsToCollection: Boolean?,

    val budget: Long,
    val genres: List<RemoteGenre>,
    val homepage: String,
    val id: Long,

    @SerialName("imdb_id")
    val imdbID: String,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("production_companies")
    val productionCompanies: List<RemoteProductionCompany>,

    @SerialName("release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    @SerialName("spoken_languages")
    val spokenLanguages: List<RemoteSpokenLanguage>,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long
)

@Serializable
data class RemoteGenre(
    val id: Long,
    val name: String
)

@Serializable
data class RemoteProductionCompany(
    val id: Long,

    @SerialName("logo_path")
    val logoPath: String? = null,

    val name: String,

    @SerialName("origin_country")
    val originCountry: String
)

@Serializable
data class RemoteSpokenLanguage(
    @SerialName("english_name")
    val englishName: String,

    @SerialName("iso_639_1")
    val iso639_1: String,

    val name: String
)
