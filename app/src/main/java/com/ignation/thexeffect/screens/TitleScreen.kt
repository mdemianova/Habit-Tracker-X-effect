package com.ignation.thexeffect.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    days: State<List<Day>>,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = HabitScreens.CreateHabitScreen.name + "/-1")
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add new Card")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        TitleContent(navController, boards, weeks, days, insertDay, deleteDay)
    }
}

@Composable
fun TitleContent(
    navController: NavController,
    boards: State<List<Board>>,
    weeks: State<List<Week>>,
    days: State<List<Day>>,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit
) {
    Surface(modifier = Modifier
        .padding(horizontal = 8.dp)
    ) {
        Column {
            if (boards.value.isEmpty()) {
                Text(
                    text = "Create a new card by pressing +",
                    modifier = Modifier.padding(top = 8.dp),
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Default
                )
            } else {
                LazyColumn(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    items(items = boards.value) { board ->
                        val boardWeeks = weeks.value.filter { it.boardId == board.id }
                        val boardDays = days.value.filter { it.boardId == board.id }
                        BoardSmall(
                            board = board,
                            weeks = boardWeeks,
                            days = boardDays,
                            onItemClick = {navController.navigate(route = HabitScreens.DetailsScreen.name + "/$it")},
                            insertDay = insertDay,
                            deleteDay = deleteDay
                        )
                    }
                }
            }
        }
    }
}