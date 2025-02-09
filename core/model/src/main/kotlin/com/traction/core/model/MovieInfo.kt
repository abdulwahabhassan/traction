package com.traction.core.model

data class MovieInfo (
    val adult: Boolean,
    val backdropPath: String,
    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,
    val imdbID: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Long,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
)

data class Genre (
    val id: Long,
    val name: String
)

data class ProductionCompany (
    val id: Long,
    val logoPath: String? = null,
    val name: String,
    val originCountry: String
)


data class SpokenLanguage (
    val englishName: String,
    val iso639_1: String,
    val name: String
)
