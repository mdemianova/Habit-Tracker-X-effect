package com.ignation.thexeffect.navigation

enum class HabitScreens {
    TitleScreen,
    CreateHabitScreen,
    DetailsScreen;

    companion object {
        fun fromRoute(route: String?): HabitScreens
        = when (route?.substringBefore("/")) {
            TitleScreen.name -> TitleScreen
            CreateHabitScreen.name -> CreateHabitScreen
            DetailsScreen.name -> DetailsScreen
            null -> TitleScreen
            else -> throw IllegalArgumentException()
        }
    }
}