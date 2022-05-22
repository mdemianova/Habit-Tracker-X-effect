package com.ignation.thexeffect.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ignation.thexeffect.components.BoardSmall
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.Week
import com.ignation.thexeffect.navigation.HabitScreens

@Composable
fun TitleScreen(
    navController: NavController,
    boards: State<List<Board>>,
    weeks: State<List<Week>>,
    days: State<List<Day>>
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
        TitleContent(navController, boards, weeks, days)
    }
}

@Composable
fun TitleContent(
    navController: NavController,
    boards: State<List<Board>>,
    weeks: State<List<Week>>,
    days: State<List<Day>>
) {
    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
    ) {
        if (boards.value.isEmpty()) {
            Text(text = "Create a new card by pressing +")
        } else {
            LazyColumn {
                items(items = boards.value) { board ->
                    BoardSmall(
                        board = board,
                        weeks = weeks.value.filter { it.boardId == board.id },
                        days = days.value.filter { it.boardId == board.id }
                    ) {
                        navController.navigate(route = HabitScreens.DetailsScreen.name + "/${it.id!!}")
                    }
                }
            }
        }
    }
}