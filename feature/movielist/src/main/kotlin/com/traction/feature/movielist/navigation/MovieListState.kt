package com.traction.feature.movielist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberMovieListState(
    navHostController: NavHostController = rememberNavController(),
): MutableState<AuthenticationState> {
    return remember(
        navHostController
    ) {
        mutableStateOf(
            AuthenticationState(
                navHostController = navHostController
            )
        )
    }
}

@Stable
data class AuthenticationState(
    val navHostController: NavHostController,
) {
    val currentDestination: NavDestination?
        @Composable get() = navHostController.currentBackStackEntryAsState().value?.destination
}

