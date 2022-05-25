package com.ignation.thexeffect.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.DayStatus
import com.ignation.thexeffect.domain.models.setActualStatus
import com.ignation.thexeffect.ui.theme.Peach

@Composable
fun DayItem(
    day: Day,
    modifier: Modifier,
    boardId: Long,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit
) {
    val dateLabel = day.date.dayOfMonth.toString()

    val dayState by remember {
        mutableStateOf(day)
    }
    dayState.boardId = boardId
    dayState.setActualStatus()


    Box(
        modifier = Modifier
            .background(Peach)
            .aspectRatio(1f)
            .clickable(
                enabled = dayState.status != DayStatus.UNAVAILABLE
            ) {
                dayState.status = when (dayState.status) {
                    DayStatus.COMPLETED -> {
                        deleteDay(dayState)
                        DayStatus.MISSED
                    }
                    DayStatus.MISSED -> {
                        insertDay(dayState)
                        DayStatus.COMPLETED
                    }
                    DayStatus.AVAILABLE -> {
                        insertDay(dayState)
                        DayStatus.COMPLETED
                    }
                    else -> throw IllegalStateException()
                }
            }
            .then(modifier)
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
        ) {
            when (dayState.status) {
                DayStatus.COMPLETED -> {
                    drawX()
                }
                DayStatus.MISSED -> {
                    drawO()
                }
                else -> {
                    drawDateLabel(dateLabel)
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DayPreview() {
//    DayItem(
//        day = Day(),
//        modifier = ,
//        boardId = ,
//        insertDay =
//    )
//}