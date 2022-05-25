package com.ignation.thexeffect.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
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

    day.boardId = boardId
    day.setActualStatus()

    Box(
        modifier = Modifier
            .background(Peach)
            .aspectRatio(1f)
            .clickable(
                enabled = day.status != DayStatus.UNAVAILABLE
            ) {
                day.status = when (day.status) {
                    DayStatus.COMPLETED -> {
                        deleteDay(day)
                        DayStatus.MISSED
                    }
                    DayStatus.MISSED -> {
                        insertDay(day)
                        DayStatus.COMPLETED
                    }
                    DayStatus.AVAILABLE -> {
                        insertDay(day)
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
            when (day.status) {
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