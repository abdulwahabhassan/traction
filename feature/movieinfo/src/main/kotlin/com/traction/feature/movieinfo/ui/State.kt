package com.traction.feature.movieinfo.ui

import com.traction.core.common.model.State
import com.traction.core.model.MovieInfo

data class MovieInfoState(
    val state: State<MovieInfo> = State.Initial
)