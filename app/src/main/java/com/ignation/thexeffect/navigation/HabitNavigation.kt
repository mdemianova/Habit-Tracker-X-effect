package com.ignation.thexeffect.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
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
    val habitViewModel = hiltViewModel<HabitViewModel>()
    val boardsState = habitViewModel.activeBoards.collectAsState()
    val weeksState = habitViewModel.allWeeks.collectAsState()
    val daysState = habitViewModel.allDays.collectAsState()

    NavHost(
        navController = navController,
        startDestination = HabitScreens.TitleScreen.name
    ) {
        composable(HabitScreens.TitleScreen.name) {
            Log.d("Navigation", "Title called")
            TitleScreen(
                navController = navController,
                boards = boardsState,
                weeks = weeksState,
                days = daysState,
                insertDay = habitViewModel::insertDay,
                deleteDay = habitViewModel::deleteDay
            )
        }

        composable(HabitScreens.CreateHabitScreen.name) {
            CreateHabitScreen(
                navController = navController,
                habitViewModel = habitViewModel
            )
        }

        composable(HabitScreens.DetailsScreen.name+"/{cardId}",
        arguments = listOf(navArgument(name = "cardId") {type = NavType.LongType})
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController,
                cardId = backStackEntry.arguments?.getLong("cardId"),
                boards = boardsState,
                weeks = weeksState,
                days = daysState,
                insertDay = habitViewModel::insertDay,
                deleteDay = habitViewModel::deleteDay
            )
        }
    }
}