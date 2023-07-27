package com.traction.feature.movielist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.traction.feature.movielist.ui.MovieListScreenRoute

const val movieListNavGraphRoute = "movie_list_nav_graph"
internal const val movieListRoute = movieListNavGraphRoute.plus("/movie_list_route")
internal const val movieListScreenRoute = movieListRoute.plus("/movie_list_screen")

internal fun NavGraphBuilder.movieListScreenRoute(
    onNavigateToMovieInfo: (Long) -> Unit,
    onUpdateLoadingState: (Boolean) -> Unit
) {
    composable(
        route = movieListScreenRoute
    ) {
        MovieListScreenRoute(
            navigateToMovieInfo = onNavigateToMovieInfo,
            onUpdateLoadingState = onUpdateLoadingState,
        )
    }
}