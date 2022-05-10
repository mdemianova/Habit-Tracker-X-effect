package com.ignation.thexeffect.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.Day
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

@Composable
fun WeekItem(
    firstDayOfWeek: LocalDate
) {
    Row {
        Text(
            text = "1",
            modifier = Modifier
                .padding(end = 5.dp)
                .align(Alignment.CenterVertically)
        )
        val date = firstDayOfWeek
        for (i in 0..6) {
            DayItem(day = Day(date = date.plus(i, DateTimeUnit.DAY)))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeek() {
    WeekItem(firstDayOfWeek = LocalDate(2022, 5, 7))
}