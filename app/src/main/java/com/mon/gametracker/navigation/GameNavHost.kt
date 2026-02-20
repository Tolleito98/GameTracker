package com.mon.gametracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mon.gametracker.features.game.ui.add.AddScreen
import com.mon.gametracker.features.game.ui.detail.DetailScreen
import com.mon.gametracker.features.game.ui.library.LibraryScreen

@Composable
fun GameNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = LibraryDestination,
        modifier = modifier
    ) {
        composable<LibraryDestination> {
            LibraryScreen(
                onNavigateToDetail = { id ->
                    navController.navigate(
                        route = GameDetailDestination(id.value)
                    )
                },
                onNavigateToAdd = {
                    navController.navigate(
                        route = AddDestination
                    )
                }
            )
        }
        composable<GameDetailDestination> {
            DetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable<AddDestination> {
            AddScreen(
                onBack = { navController.popBackStack() },
                onNavigateToDetail = { id ->
                    navController.navigate(
                        route = GameDetailDestination(id.value)
                    )
                }
            )
        }

    }
}