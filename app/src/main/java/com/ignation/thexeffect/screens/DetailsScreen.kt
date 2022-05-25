package com.ignation.thexeffect.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ignation.thexeffect.components.BoardFull
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.Week

@Composable
fun DetailsScreen(
    navController: NavController,
    cardId: Long?,
    boards: State<List<Board>>,
    weeks: State<List<Week>>,
    days: State<List<Day>>,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Text(text = "Card Details")
            }
        }
    ) {
        Surface(modifier = Modifier.padding(8.dp)) {
            Column {
                BoardFull(
                    board = boards.value.filter { it.id == cardId }[0],
                    weeks = weeks.value.filter { it.boardId == cardId },
                    days = days.value.filter { it.boardId == cardId },
                    insertDay = insertDay,
                    deleteDay = deleteDay
                )
            }
        }
    }
}