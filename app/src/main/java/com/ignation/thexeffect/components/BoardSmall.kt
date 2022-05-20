package com.ignation.thexeffect.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.*
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus

@Composable
fun BoardSmall(
    board: Board,
    weeks: List<Week>,
    days: List<Day>,
    onItemClick: (Board) -> Unit
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
        ) {
            Surface(
                modifier = Modifier
                    .clickable {
                        onItemClick(board)
                    }
                    .fillMaxWidth(),
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
                if (startInFuture) {
                    WeekItem(weekIndex = 1, firstDayOfWeek = board.startDate, days = days)
                } else {
                    WeekItem(weekIndex, firstDayOfWeek, days)
                }
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.LightGray,
                border = BorderStroke(2.dp, Color.Gray)
            ) {
                if (currentWeek != null) {
                    Text(
                        text = currentWeek.comment
                    )
                } else {
                    Log.d("BoardSmall", "i got null week")
                }
            }
        }
    }
}

//val testBoard = Board(
//    title = "Become a superhero",
//    isActive = true,
//    startDate = LocalDate(2022, 5, 1),
//    isCreateHabit = true,
//    weeks = listOf(
//        Week(2, "This is week 2"),
//        Week(3, "This is week 3")
//    ),
//    days = listOf(
//        //Day(DayStatus.COMPLETED, LocalDate(2022, 5, 10))
//    )
//)

//@Preview(showBackground = true)
//@Composable
//fun PreviewBoardSmall() {
//    BoardSmall(testBoard) { Log.d("BoardSmall", "PreviewBoardSmall: clicked")}
//}