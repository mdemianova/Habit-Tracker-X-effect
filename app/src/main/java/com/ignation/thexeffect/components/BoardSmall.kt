package com.ignation.thexeffect.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ignation.thexeffect.domain.models.Board
import java.util.*

@Composable
fun BoardSmall(
    board: Board
) {

}

fun getActiveWeek(board: Board) {
    val startDay = board.startDate
    val today = Calendar.getInstance()
}


fun testCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(2022, 5, 4)
    return calendar
}

val testBoard = Board(
    title = "Stop playing video games",
    isActive = true,
    startDate = testCalendar()
)

@Preview
@Composable
fun PreviewBoardSmall() {
    BoardSmall(testBoard)
}