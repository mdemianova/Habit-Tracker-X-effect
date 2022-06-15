package com.ignation.thexeffect.components

import androidx.compose.foundation.BorderStroke
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

@Composable
fun BoardFull(
    board: Board,
    weeks: List<Week>,
    days: List<Day>,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit
) {
    val boardDays = days.filter { it.boardId == board.id }

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp),
        border = BorderStroke(2.dp, CardBorder)
    ) {
        Column {
            BoardTitle(
                titleText = board.title,
                boardType = board.isCreateHabit,
                clickable = false
            )

            BoardBodyFull(
                boardId = board.id,
                boardStartDate = board.startDate,
                boardDays = boardDays,
                insertDay = insertDay,
                deleteDay = deleteDay
            )

            val weekIndex = getCurrentWeekIndex(board)
            val currentWeek = getCurrentWeek(weeks, weekIndex)
            BoardComment(week = currentWeek)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBoardFull() {
    BoardFull(board = testBoard, weeks = listOf(), days = listOf(), insertDay = {}, deleteDay = {})
}