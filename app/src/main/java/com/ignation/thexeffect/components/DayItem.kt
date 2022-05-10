package com.ignation.thexeffect.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.DayStatus
import com.ignation.thexeffect.domain.models.setActualStatus
import com.ignation.thexeffect.domain.models.statusChange

const val DAY_COMPONENT_SIZE = 34

@Composable
fun DayItem(
    day: Day
) {
    val dateText = day.date.dayOfMonth.toString()
    day.setActualStatus()

    val dayState by remember {
        mutableStateOf(day)
    }

    Box(
        modifier = Modifier
            .clickable(
                enabled = dayState.status != DayStatus.UNAVAILABLE
            ) {
            dayState.statusChange()
        }
    ) {
        Canvas(
            modifier = Modifier
                .size(DAY_COMPONENT_SIZE.dp)
        ) {
            drawBox()

            when (dayState.status) {
                DayStatus.COMPLETED -> {
                    drawX()
                }
                DayStatus.MISSED -> {
                    drawO()
                }
                else -> {
                    drawDateLabel(dateText)
                }
            }
        }
    }
}