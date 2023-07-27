package com.traction.feature.movielist.ui

import com.traction.core.model.Category

sealed interface MovieListScreenEvent {
    class OnFilterSelected(val category: Category): MovieListScreenEvent
    object OnRetry: MovieListScreenEvent
}
