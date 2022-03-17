package com.ignation.thexeffect.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.model.Day

@Composable
fun WeekComponent(
    modifier: Modifier = Modifier
) {
    Row {
        Text(
            text = "1",
            modifier
                .padding(end = 5.dp)
                .align(Alignment.CenterVertically)
        )
        DayComponent(Day())
        DayComponent(Day())
        DayComponent(Day())
        DayComponent(Day())
        DayComponent(Day())
        DayComponent(Day())
        DayComponent(Day())
    }
}