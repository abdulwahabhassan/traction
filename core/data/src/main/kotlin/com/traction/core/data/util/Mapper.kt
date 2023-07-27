package com.traction.core.data.util

import com.traction.core.data.database.entity.GenreEntity
import com.traction.core.data.database.entity.ProductionCompanyEntity
import com.traction.core.data.database.entity.SpokenLanguageEntity
import com.traction.core.model.Genre
import com.traction.core.model.MovieInfo
import com.traction.core.model.ProductionCompany
import com.traction.core.model.SpokenLanguage
import com.traction.core.data.database.entity.LocalMovie
import com.traction.core.data.database.entity.LocalMovieInfo
import com.traction.core.model.Movie
import com.traction.core.network.model.RemoteGenre
import com.traction.core.network.model.RemoteMovie
import com.traction.core.network.model.RemoteMovieInfo
import com.traction.core.network.model.RemoteProductionCompany
import com.traction.core.network.model.RemoteSpokenLanguage


fun RemoteMovie.asMovie(): Movie {
    return Movie(
        this.adult,
        "https://image.tmdb.org/t/p/original" + this.backdropPath,
        this.id,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        "https://image.tmdb.org/t/p/original" + this.posterPath,
        this.releaseDate,
        this.title,
        this.video,
        this.voteAverage,
        this.voteCount
    )
}

fun RemoteGenre.asGenre(): Genre {
    return Genre(this.id, this.name)
}

fun RemoteProductionCompany.asProductionCompany(): ProductionCompany {
    return ProductionCompany(
        this.id,
        this.logoPath,
        this.name,
        this.originCountry
    )
}

fun RemoteSpokenLanguage.asSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(
        this.englishName,
        this.iso639_1,
        this.name
    )
}

fun RemoteMovieInfo.asMovieInfo(): MovieInfo {
    return MovieInfo(
        this.adult,
        "https://image.tmdb.org/t/p/original" + this.backdropPath,
        this.budget,
        this.genres.map { remoteGenre -> remoteGenre.asGenre() },
        this.homepage,
        this.id,
        this.imdbID,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        "https://image.tmdb.org/t/p/original" + this.posterPath,
        this.productionCompanies.map { remoteProductionCompany ->
            remoteProductionCompany.asProductionCompany()
        },
        this.releaseDate,
        this.revenue,
        this.runtime,
        this.spokenLanguages.map { remoteSpokenLanguage ->
            remoteSpokenLanguage.asSpokenLanguage()
        },
        this.status,
        this.tagline,
        this.originalTitle,
        this.video,
        this.voteAverage,
        this.voteCount
    )
}

fun GenreEntity.asGenre(): Genre {
    return Genre(this.id, this.name)
}

fun ProductionCompanyEntity.asProductionCompany(): ProductionCompany {
    return ProductionCompany(
        this.id,
        this.logoPath,
        this.name,
        this.originCountry
    )
}

fun SpokenLanguageEntity.asSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(
        this.englishName,
        this.iso639_1,
        this.name
    )
}

fun LocalMovieInfo.asMovieInfo(): MovieInfo {
    return MovieInfo(
        this.adult,
        this.backdropPath,
        this.budget,
        this.genres.map { genreEntity -> genreEntity.asGenre() },
        this.homepage,
        this.id,
        this.imdbID,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.productionCompanies.map { productCompanyEntity ->
            productCompanyEntity.asProductionCompany()
        },
        this.releaseDate,
        this.revenue,
        this.runtime,
        this.spokenLanguages.map { spokenLanguageEntity ->
            spokenLanguageEntity.asSpokenLanguage()
        },
        this.status,
        this.tagline,
        this.originalTitle,
        this.video,
        this.voteAverage,
        this.voteCount
    )
}

fun LocalMovie.asMovie(): Movie {
    return Movie(
        this.adult,
        this.backdropPath,
        this.id,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.releaseDate,
        this.title,
        this.video,
        this.voteAverage,
        this.voteCount
    )

}

fun Movie.asLocalMovie(): LocalMovie {
    return LocalMovie(
        this.id,
        this.adult,
        this.backdropPath,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.releaseDate,
        this.title,
        this.video,
        this.voteAverage,
        this.voteCount,
        ""
    )
}

fun MovieInfo.asLocalMovieInfo(): LocalMovieInfo {
    return LocalMovieInfo(
        this.id,
        this.adult,
        this.backdropPath,
        this.budget,
        this.genres.map { genre -> genre.asGenreEntity() },
        this.homepage,
        this.imdbID,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.productionCompanies.map { productCompany ->
            productCompany.asProductionCompanyEntity()
        },
        this.releaseDate,
        this.revenue,
        this.runtime,
        this.spokenLanguages.map { spokenLanguage ->
            spokenLanguage.asSpokenLanguageEntity()
        },
        this.status,
        this.tagline,
        this.originalTitle,
        this.video,
        this.voteAverage,
        this.voteCount
    )
}

fun Genre.asGenreEntity(): GenreEntity {
    return GenreEntity(this.id, this.name)
}

fun ProductionCompany.asProductionCompanyEntity(): ProductionCompanyEntity {
    return ProductionCompanyEntity(
        this.id,
        this.logoPath,
        this.name,
        this.originCountry
    )
}

fun SpokenLanguage.asSpokenLanguageEntity(): SpokenLanguageEntity {
    return SpokenLanguageEntity(
        this.englishName,
        this.iso639_1,
        this.name
    )
}