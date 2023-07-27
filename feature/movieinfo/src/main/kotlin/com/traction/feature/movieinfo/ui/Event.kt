package com.traction.feature.movieinfo.ui

sealed interface MovieInfoScreenEvent {
    class OnGetMovieInfo(val movieId: Long): MovieInfoScreenEvent
}
