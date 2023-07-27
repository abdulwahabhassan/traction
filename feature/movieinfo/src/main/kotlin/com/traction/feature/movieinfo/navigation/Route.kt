package com.traction.feature.movieinfo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.traction.feature.movieinfo.ui.MovieInfoRoute

const val movieInfoNavGraphRoute = "movie_info_nav_graph"
internal const val movieInfoRoute = movieInfoNavGraphRoute.plus("/movie_info_route")
internal const val movieInfoScreenRoute = movieInfoRoute.plus("/movie_info_screen")

internal fun NavGraphBuilder.movieInfoScreenRoute(
    onBackPress: () -> Unit,
    movieId: Long
) {
    composable(
        route = movieInfoScreenRoute
    ) {
        MovieInfoRoute(
            onBackPress = onBackPress,
            movieId = movieId,
        )

    }
}