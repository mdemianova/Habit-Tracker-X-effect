package com.ignation.thexeffect.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ignation.thexeffect.HabitViewModel
import com.ignation.thexeffect.components.BoardSmall
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.navigation.HabitScreens

@Composable
fun TitleScreen(navController: NavController) {
    val viewModel: HabitViewModel = hiltViewModel()
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
        val boards = viewModel.activeBoards.value ?: listOf()
        TitleContent(boards, navController)
    }
}

@Composable
fun TitleContent(
    boards: List<Board>,
    navController: NavController
) {

    Text(text = "Title Screen")
    LazyColumn {
        items(items = boards) {
            BoardSmall(board = it) { board ->
                navController.navigate(route = HabitScreens.DetailsScreen.name + "/${board.id}")
            }
        }
    }
}