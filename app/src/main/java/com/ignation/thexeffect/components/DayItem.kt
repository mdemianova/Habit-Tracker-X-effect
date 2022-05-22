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
import com.ignation.thexeffect.domain.models.statusChange
import com.ignation.thexeffect.ui.theme.Peach

@Composable
fun DayItem(
    day: Day,
    modifier: Modifier
) {
    val dateText = day.date.dayOfMonth.toString()
    day.setActualStatus()

    val dayState by remember {
        mutableStateOf(day)
    }

    Box(
        modifier = Modifier
            .background(Peach)
            .aspectRatio(1f)
            .clickable(
                enabled = dayState.status != DayStatus.UNAVAILABLE
            ) {
                dayState.statusChange()
            }.then(modifier)
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
                    drawDateLabel(dateText)
                }
            }
        }
    }
}