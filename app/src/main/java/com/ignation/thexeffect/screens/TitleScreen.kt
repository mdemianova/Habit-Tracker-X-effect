package com.ignation.thexeffect.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.NavController
import com.ignation.thexeffect.components.BoardSmall
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.navigation.HabitScreens

@Composable
fun TitleScreen(
    navController: NavController,
    boards: State<List<Board>>
) {
    Scaffold(
        topBar = {
            Text(text = "Title")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = HabitScreens.CreateHabitScreen.name)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add new Card")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        TitleContent(navController, boards)
    }
}

@Composable
fun TitleContent(
    navController: NavController,
    boards: State<List<Board>>
) {
    if (boards.value.isEmpty()) {
        Text(text = "Create a new habit card by pressing +")
    } else {
        LazyColumn {
            items(items = boards.value) {
                BoardSmall(board = it) { board ->
                    navController.navigate(route = HabitScreens.DetailsScreen.name + "/${board.id}")
                }
            }
        }
    }

}