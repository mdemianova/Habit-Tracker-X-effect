package com.ignation.thexeffect.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ignation.thexeffect.HabitViewModel
import com.ignation.thexeffect.screens.CreateHabitScreen
import com.ignation.thexeffect.screens.DetailsScreen
import com.ignation.thexeffect.screens.TitleScreen

@Composable
fun HabitNavigation() {

    val navController = rememberNavController()
    val habitViewModel = viewModel<HabitViewModel>()

    NavHost(
        navController = navController,
        startDestination = HabitScreens.TitleScreen.name
    ) {
        composable(HabitScreens.TitleScreen.name) {
            TitleScreen(
                navController,
                habitViewModel.activeBoards.collectAsState(),
                habitViewModel.allWeeks.collectAsState(),
                habitViewModel.allDays.collectAsState()
            )
        }

        composable(HabitScreens.CreateHabitScreen.name) {
            CreateHabitScreen(navController, habitViewModel)
        }

        composable(HabitScreens.DetailsScreen.name+"/{cardId}",
        arguments = listOf(navArgument(name = "cardId") {type = NavType.LongType})
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController,
                backStackEntry.arguments?.getLong("cardId")
            )
        }
    }
}