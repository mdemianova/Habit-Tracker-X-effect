package com.ignation.thexeffect.components

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Week
import kotlinx.datetime.*
import kotlin.math.ceil

@Composable
fun BoardSmall(
    board: Board
) {
    val weekIndex = getCurrentWeekIndex(board)
    val currentWeek = getCurrentWeek(board, weekIndex)
    val firstDayOfWeek = board.startDate.plus(weekIndex * 7, DateTimeUnit.DAY)

    Surface {
        Text(text = board.title)
        WeekItem(firstDayOfWeek)
        if (currentWeek != null) {
            Text(text = currentWeek.comment)
        }
    }
}

fun getCurrentWeek(board: Board, currentWeekIndex: Int): Week? {

    return if (board.weeks.isNullOrEmpty()) {
        null
    } else {
        for (item in board.weeks!!) {
            if (item.index == currentWeekIndex) return item
        }
        null
    }
}


fun getCurrentWeekIndex(board: Board): Int {
    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    val startDate = board.startDate
    val indexOfCurrentDayInBoard = startDate.daysUntil(currentDate) + 1

    return ceil(indexOfCurrentDayInBoard / 7f).toInt()
}

val testBoard = Board(
    title = "Stop playing video games",
    isActive = true,
    startDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
    isCreateHabit = true
)

@Preview
@Composable
fun PreviewBoardSmall() {
    BoardSmall(testBoard)
}