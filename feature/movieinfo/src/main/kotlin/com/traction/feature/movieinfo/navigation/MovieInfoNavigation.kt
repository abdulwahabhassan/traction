package com.traction.feature.movieinfo.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument

fun NavGraphBuilder.movieInfoNavGraph(
    onBackPress: () -> Unit,
    appNavController: NavHostController
) {
    navigation(
        route = "$movieInfoNavGraphRoute/{$movieIdArg}",
        startDestination = movieInfoRoute,
        arguments = listOf(
            navArgument(movieIdArg) { type = NavType.LongType },
        )
    ) {
        composable(route = movieInfoRoute) { backStackEntry: NavBackStackEntry ->
            val parentEntry = remember(backStackEntry){
                appNavController.getBackStackEntry(movieInfoRoute)
            }
            val movieId = parentEntry.arguments?.getLong(movieIdArg)
            val movieInfoState by rememberMovieInfoState()

            if (movieId != null) {
                MovieInfoNavHost(
                    navHostController = movieInfoState.navHostController,
                    onBackPress = onBackPress,
                    movieId = movieId.toLong()
                )
            }
        }
    }
}

@Composable
fun MovieInfoNavHost(
    navHostController: NavHostController,
    onBackPress: () -> Unit,
    movieId: Long
) {
    NavHost(
        modifier = Modifier,
        navController = navHostController,
        startDestination = movieInfoScreenRoute,
    ) {
        movieInfoScreenRoute(
            onBackPress = onBackPress,
            movieId = movieId
        )
    }
}



