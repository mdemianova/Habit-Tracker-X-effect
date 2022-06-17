package com.ignation.thexeffect.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.DayStatus
import com.ignation.thexeffect.domain.models.setActualStatus
import com.ignation.thexeffect.ui.theme.Peach

@Composable
fun DayItem(
    day: Day,
    modifier: Modifier,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit,
) {
    val dateLabel = day.date.dayOfMonth.toString()

    day.setActualStatus()

    Box(
        modifier = Modifier
            .background(
                when (day.status) {
                    DayStatus.UNAVAILABLE -> Color.LightGray
                    else -> Peach
                })
            .border(
                if (day.status == DayStatus.AVAILABLE) {
                    BorderStroke(2.dp, Color.Magenta)
                } else {
                    BorderStroke(0.dp, Color.Transparent)
                }
            )
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