package com.traction.feature.movielist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.traction.core.common.Extension.capitalizeEachWord
import com.traction.core.common.model.State
import com.traction.core.designsystem.theme.TractionTheme
import com.traction.core.designsystem.ui.ErrorScreen
import com.traction.core.designsystem.ui.LoadingScreen
import com.traction.feature.movielist.ui.component.FilterDialog
import com.traction.feature.movielist.ui.component.Header
import com.traction.feature.movielist.ui.component.movieListGrid

@Composable
fun MovieListScreenRoute(
    viewModel: MovieListViewModel = hiltViewModel(),
    navigateToMovieInfo: (Long) -> Unit,
    onUpdateLoadingState: (Boolean) -> Unit
) {
    val screenState by viewModel.state.collectAsStateWithLifecycle()
    MovieListScreen(
        screenState = screenState,
        onNavigateToMovieInfo = navigateToMovieInfo,
        onUpdateLoadingState = onUpdateLoadingState,
        onUiEvent = { uiEvent: MovieListScreenEvent ->
            viewModel.sendEvent(uiEvent)
        }
    )
}

@Composable
fun MovieListScreen(
    screenState: MovieListsScreenState,
    onUpdateLoadingState: (Boolean) -> Unit,
    onNavigateToMovieInfo: (Long) -> Unit,
    onUiEvent: (MovieListScreenEvent) -> Unit
) {
    val lazyGridState = rememberLazyGridState()
    val movies =
        if (screenState.state is State.Success) screenState.state.data.collectAsLazyPagingItems() else null
    val showFilterOptions = rememberSaveable { mutableStateOf(false) }

    onUpdateLoadingState(
        movies?.loadState?.append == LoadState.Loading ||
                movies?.loadState?.refresh == LoadState.Loading
    )

    when (screenState.state) {
        is State.Success -> {
            Column(modifier = Modifier.fillMaxSize()) {
                Header(
                    category = screenState.category.capitalizeEachWord(),
                    onFilterClick = {
                        showFilterOptions.value = true
                    }
                )
                val movieListIsEmpty = movies?.itemCount?.let { it == 0 } ?: true
                val isNotLoading = (movies?.loadState?.append == LoadState.Loading ||
                        movies?.loadState?.refresh == LoadState.Loading).not()
                if (movieListIsEmpty && isNotLoading) {
                    ErrorScreen(
                        message = "We are sorry, list of ${
                            screenState.category.replace("_", " ")
                                .capitalizeEachWord()
                        } movies could not be retrieved",
                        onRetryClick = {
                            onUiEvent(MovieListScreenEvent.OnRetry)
                        }
                    )
                } else if (movieListIsEmpty) {
                    LoadingScreen()
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        state = lazyGridState,
                        contentPadding = PaddingValues(4.dp)
                    ) {
                        movieListGrid(
                            movies = movies!!,
                            onNavigateToMovieInfo = onNavigateToMovieInfo
                        )
                    }
                }
            }
        }

        is State.Error -> {
            ErrorScreen(
                message = screenState.state.message,
                onRetryClick = { onUiEvent(MovieListScreenEvent.OnRetry) }
            )
        }

        is State.Loading, is State.Initial -> {
            //Unused, do nothing
        }
    }

    if (showFilterOptions.value) {
        FilterDialog(
            onDismissDialog = { showFilterOptions.value = false },
            onUiEvent = onUiEvent
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MovieListScreenPreview() {
    TractionTheme {
        MovieListScreen(
            screenState = MovieListsScreenState(),
            onUpdateLoadingState = {},
            onNavigateToMovieInfo = {},
            onUiEvent = {}
        )
    }
}
