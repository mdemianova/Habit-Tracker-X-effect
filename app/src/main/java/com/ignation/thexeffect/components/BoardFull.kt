package com.ignation.thexeffect.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.Week
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
                days = days.filter { it.boardId == board.id },
                boardId = board.id!!,
                insertDay = insertDay,
                deleteDay = deleteDay
            )
            startDate = startDate.plus(7, DateTimeUnit.DAY)
        }
    }
}