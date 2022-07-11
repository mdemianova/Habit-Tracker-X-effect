package com.ignation.thexeffect.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ignation.thexeffect.components.BoardFull
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.Week
import com.ignation.thexeffect.navigation.HabitScreens
import com.ignation.thexeffect.utils.CurrentDate

@Composable
fun DetailsScreen(
    navController: NavController,
    cardId: Long?,
    boards: State<List<Board>>,
    weeks: State<List<Week>>,
    days: State<List<Day>>,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit,
    deleteCard: (Board) -> Unit
) {
    Log.d("Navigation", "DetailsScreen recomposes")
    val filteredBoards = boards.value.filter { it.id == cardId }
    val detailsBoard =
        if (filteredBoards.isNotEmpty()) {
            filteredBoards[0]
        } else {
            Board(
                id = -1,
                title = "",
                isActive = false,
                startDate = CurrentDate.now,
                isCreateHabit = true
            )
        }
    val detailsWeek = weeks.value.filter { it.boardId == cardId }
    val detailsDay = days.value.filter { it.boardId == cardId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Card Details") },
                navigationIcon = {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            )
        }
    ) {
        Surface(modifier = Modifier.padding(8.dp)) {

            val showAlert = remember {
                mutableStateOf(false)
            }
            Column {
                BoardFull(
                    board = detailsBoard,
                    weeks = detailsWeek,
                    days = detailsDay,
                    insertDay = insertDay,
                    deleteDay = deleteDay
                )

                Row(
                    modifier = Modifier.fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            navController.navigate(HabitScreens.CreateHabitScreen.name + "/$cardId")
                        },
                        modifier = Modifier.padding(end = 20.dp)
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                        Text(text = "Edit Card")
                    }
                    OutlinedButton(onClick = {
                        showAlert.value = true
                    }) {
                        Text(text = "Delete Card")
                    }
                }

                if (showAlert.value) {
                    AlertDialog(
                        onDismissRequest = { showAlert.value = false },
                        confirmButton = {
                            TextButton(onClick = {
                                navController.navigate(route = HabitScreens.TitleScreen.name)
                                deleteCard(detailsBoard)
                            }) {
                                Text(text = "Yes")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showAlert.value = false }) {
                                Text(text = "No")
                            }
                        },
                        title = {
                            Text(text = "Do you want to delete this Card?")
                        }
                    )
                }
            }
        }
    }
}