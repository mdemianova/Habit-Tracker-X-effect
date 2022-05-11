package com.ignation.thexeffect.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ignation.thexeffect.screens.CreateHabitScreen
import com.ignation.thexeffect.screens.TitleScreen

@Composable
fun HabitNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HabitScreens.TitleScreen.name
    ) {
        composable(HabitScreens.TitleScreen.name) {
            TitleScreen(navController)
        }

        composable(HabitScreens.CreateHabitScreen.name) {
            CreateHabitScreen(navController)
        }
    }
}