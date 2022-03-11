package com.ignation.thexeffect.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.model.Week

@Composable
fun WeekComponent(
    week: Week,
    modifier: Modifier = Modifier
) {
    Row {
        Text(
            text = "${week.index}",
            Modifier
                .padding(end = 5.dp)
                .align(Alignment.CenterVertically)
        )
        DayComponent(day = week.dayOne)
        DayComponent(day = week.dayTwo)
        DayComponent(day = week.dayThree)
        DayComponent(day = week.dayFour)
        DayComponent(day = week.dayFive)
        DayComponent(day = week.daySix)
        DayComponent(day = week.daySeven)
    }
}