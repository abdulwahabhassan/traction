package com.traction.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.traction.feature.movieinfo.navigation.movieInfoNavGraph
import com.traction.feature.movieinfo.navigation.movieInfoNavGraphRoute
import com.traction.feature.movielist.navigation.movieListNavGraph
import com.traction.feature.movielist.navigation.movieListNavGraphRoute

@Composable
fun AppNavHost(
    appNavController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = movieListNavGraphRoute,
    onUpdateLoadingState: (Boolean) -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = appNavController,
        startDestination = startDestination,
    ) {
        movieListNavGraph(
            onUpdateLoadingState = onUpdateLoadingState,
            onNavigateToMovieInfo = { movieId: Long ->
                val encodedMovieId = Uri.encode(movieId.toString())
                val navOption = navOptions {
                    popUpTo(appNavController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                appNavController.navigate(
                    route = "$movieInfoNavGraphRoute/$encodedMovieId",
                    navOption
                )
            }
        )
        movieInfoNavGraph(
            onBackPress = {
                appNavController.popBackStack()
            },
            appNavController = appNavController
        )
    }
}



