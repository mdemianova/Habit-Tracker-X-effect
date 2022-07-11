package com.ignation.thexeffect.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ignation.thexeffect.components.BoardSmall
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.Week
import com.ignation.thexeffect.navigation.HabitScreens
import com.ignation.thexeffect.ui.theme.Orange500

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
    Surface(
        modifier = Modifier
            .padding(8.dp)
    ) {
        if (boards.value.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Surface(
                    modifier = Modifier.align(Alignment.Center),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, Orange500)
                ) {
                    Text(
                        text = "Create a new card by pressing +",
                        modifier = Modifier
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(items = boards.value) { board ->
                        val boardWeeks = weeks.value.filter { it.boardId == board.id }
                        val boardDays = days.value.filter { it.boardId == board.id }
                        BoardSmall(
                            board = board,
                            weeks = boardWeeks,
                            days = boardDays,
                            onItemClick = { navController.navigate(route = HabitScreens.DetailsScreen.name + "/$it") },
                            insertDay = insertDay,
                            deleteDay = deleteDay
                        )
                    }
                }
            }
        }
    }
}