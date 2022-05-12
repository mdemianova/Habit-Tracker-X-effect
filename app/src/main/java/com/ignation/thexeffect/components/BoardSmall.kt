package com.ignation.thexeffect.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.*
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

@Composable
fun BoardSmall(
    board: Board,
    onItemClick: (Board) -> Unit
) {
    val weekIndex = getCurrentWeekIndex(board)
    val currentWeek = getCurrentWeekFromBoard(board, weekIndex)
    val firstDayOfWeek = board.startDate.plus((weekIndex - 1) * 7, DateTimeUnit.DAY)

    Surface(
        shape = RoundedCornerShape(CornerSize(10.dp)),
        border = BorderStroke(2.dp, Color.DarkGray)
    ) {
        Column(
        ) {
            Surface(
                modifier = Modifier.clickable {
                    onItemClick(board)
                },
                color = Color.Red,
                border = BorderStroke(2.dp, Color.DarkGray)
            ) {
                Text(
                    text = board.title,
                    modifier = Modifier
                        .padding(top = 5.dp, start = 5.dp, bottom = 4.dp)
                )
            }
            Surface(
                modifier = Modifier.padding(start = 6.dp)
            ) {
                WeekItem(weekIndex, firstDayOfWeek, board.days)
            }

            Surface(
                color = Color.LightGray,
                border = BorderStroke(2.dp, Color.Gray)
            ) {
                if (currentWeek != null) {
                    Text(
                        text = currentWeek.comment,
                        modifier = Modifier.padding()
                    )
                }
            }
        }
    }
}

val testBoard = Board(
    title = "Become a superhero",
    isActive = true,
    startDate = LocalDate(2022, 4, 25),
    isCreateHabit = true,
    weeks = listOf(
        Week(2, "This is week 2"),
        Week(3, "This is week 3")
    ),
    days = listOf(
        Day(DayStatus.COMPLETED, LocalDate(2022, 5, 10))
    )
)

@Preview(showBackground = true)
@Composable
fun PreviewBoardSmall() {
    BoardSmall(testBoard) { Log.d("BoardSmall", "PreviewBoardSmall: clicked")}
}