package com.traction.feature.movielist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.movieListNavGraph(
    onUpdateLoadingState: (Boolean) -> Unit,
    onNavigateToMovieInfo: (Long) -> Unit
) {
    navigation(
        route = movieListNavGraphRoute,
        startDestination = movieListRoute,
    ) {
        composable(route = movieListRoute) {
            val movieListState by rememberMovieListState()
            MovieListNavHost(
                navHostController = movieListState.navHostController,
                onUpdateLoadingState = onUpdateLoadingState,
                onNavigateToMovieInfo = onNavigateToMovieInfo
            )
        }
    }
}

@Composable
fun MovieListNavHost(
    navHostController: NavHostController,
    onUpdateLoadingState: (Boolean) -> Unit,
    onNavigateToMovieInfo: (Long) -> Unit
) {
    NavHost(
        modifier = Modifier,
        navController = navHostController,
        startDestination = movieListScreenRoute,
    ) {
        movieListScreenRoute(
            onNavigateToMovieInfo = onNavigateToMovieInfo,
            onUpdateLoadingState = onUpdateLoadingState,
        )
    }
}



