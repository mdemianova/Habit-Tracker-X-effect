package com.ignation.thexeffect.navigation

enum class HabitScreens {
    TitleScreen,
    CreateHabitScreen;

    companion object {
        fun fromRoute(route: String?): HabitScreens
        = when (route?.substringBefore("/")) {
            TitleScreen.name -> TitleScreen
            CreateHabitScreen.name -> CreateHabitScreen
            else -> TitleScreen
        }
    }
}