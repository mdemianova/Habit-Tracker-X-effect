package com.ignation.thexeffect.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.*
import com.ignation.thexeffect.ui.theme.CardBorder
import kotlinx.datetime.LocalDate

@Composable
fun BoardSmall(
    board: Board,
    weeks: List<Week>,
    days: List<Day>,
    onItemClick: (Long) -> Unit,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit
) {
    val weekIndex = getCurrentWeekIndex(board)
    val currentWeek = getCurrentWeek(weeks, weekIndex)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(2.dp, CardBorder)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            BoardTitle(
                titleText = board.title,
                boardType = board.isCreateHabit,
                boardId = board.id,
                clickable = true,
                onItemClick = onItemClick
            )

            BoardBodySmall(
                startDate = board.startDate,
                boardId = board.id,
                weekIndex = weekIndex,
                days = days,
                insertDay = insertDay,
                deleteDay = deleteDay
            )
            BoardComment(week = currentWeek)
        }
    }
}

val testBoard = Board(
    id = 2,
    title = "Become a superhero",
    isActive = true,
    startDate = LocalDate(2022, 7, 3),
    isCreateHabit = true
)

val testWeeks = listOf(
    Week(2, 1, "1"),
    Week(2, 2, "2"),
    Week(2, 3, "3"),
)

@Preview(showBackground = true)
@Composable
fun PreviewBoardSmall() {
    BoardSmall(board = testBoard, weeks = testWeeks, listOf(), {}, {}, {})
}