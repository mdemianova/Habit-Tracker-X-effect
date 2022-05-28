package com.ignation.thexeffect.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.*
import com.ignation.thexeffect.ui.theme.BreakColor
import com.ignation.thexeffect.ui.theme.CreateColor
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

@Composable
fun BoardSmall(
    board: Board,
    weeks: List<Week>,
    days: List<Day>,
    onItemClick: (Board) -> Unit,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit
) {
    val weekIndex = getCurrentWeekIndex(board)
    val currentWeek = getCurrentWeek(weeks, weekIndex)
    val firstDayOfWeek = board.startDate.plus((weekIndex - 1) * 7, DateTimeUnit.DAY)
    val startInFuture = weekIndex <= 0

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp),
        border = BorderStroke(2.dp, Color.DarkGray)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Surface(
                modifier = Modifier
                    .clickable { onItemClick(board) }
                    .fillMaxWidth(),
                color = if (board.isCreateHabit) CreateColor else BreakColor,
                border = BorderStroke(2.dp, Color.DarkGray)
            ) {
                Text(
                    text = board.title,
                    modifier = Modifier
                        .padding(top = 5.dp, start = 5.dp, bottom = 4.dp)
                )
            }

            Surface(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {
                if (startInFuture) {
                    WeekItem(
                        weekIndex = 1,
                        firstDayOfWeek = board.startDate,
                        days = days,
                        boardId = board.id!!,
                        insertDay = insertDay,
                        deleteDay = deleteDay
                    )
                } else {
                    WeekItem(
                        weekIndex = weekIndex,
                        firstDayOfWeek = firstDayOfWeek,
                        days = days,
                        boardId = board.id!!,
                        insertDay = insertDay,
                        deleteDay = deleteDay
                    )
                }
            }

            Surface(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (currentWeek != null) {
                    Text(
                        text = currentWeek.comment
                    )
                }
            }
        }
    }
}

val testBoard = Board(
    id = 2,
    title = "Become a superhero",
    isActive = true,
    startDate = LocalDate(2022, 5, 22),
    isCreateHabit = true
)

@Preview(showBackground = true)
@Composable
fun PreviewBoardSmall() {
    BoardSmall(testBoard, listOf(), listOf(), {}, {},{})
}