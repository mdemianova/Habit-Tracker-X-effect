package com.ignation.thexeffect.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.*
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus

@Composable
fun BoardFull(
    board: Board,
    weeks: List<Week>,
    days: List<Day>,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit
) {

    val boardDays = days.filter { it.boardId == board.id }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = board.title,
            modifier = Modifier.padding(start = 14.dp)
        )
        var startDate = board.startDate

        for (i in 1..7) {
            WeekItem(
                weekIndex = i,
                firstDayOfWeek = startDate,
                days = boardDays,
                boardId = board.id!!,
                insertDay = insertDay,
                deleteDay = deleteDay
            )
            startDate = startDate.plus(7, DateTimeUnit.DAY)
        }
        val weekIndex = getCurrentWeekIndex(board)
        val currentWeek = getCurrentWeek(weeks, weekIndex)
        if (currentWeek != null) {
            Text(text = currentWeek.comment)
        }
    }
}