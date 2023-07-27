package com.traction.feature.movielist.ui

import androidx.paging.PagingData
import com.traction.core.model.Category
import com.traction.core.common.model.State
import com.traction.core.model.Movie
import kotlinx.coroutines.flow.Flow

data class MovieListsScreenState(
    val state: State<Flow<PagingData<Movie>>> = State.Initial,
    val category: String = Category.POPULAR.name
)