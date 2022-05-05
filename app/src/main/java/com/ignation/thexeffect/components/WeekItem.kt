package com.ignation.thexeffect.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.Day
import java.util.*

@Composable
fun WeekItem(
    modifier: Modifier = Modifier
) {
    Row {
        Text(
            text = "1",
            modifier
                .padding(end = 5.dp)
                .align(Alignment.CenterVertically)
        )
        for (i in 1..7) {
            DayItem(day = Day(date = Calendar.getInstance()))
        }
    }
}