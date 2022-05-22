package com.ignation.thexeffect.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
    weekIndex: Int,
    firstDayOfWeek: LocalDate,
    days: List<Day>?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "$weekIndex",
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
        val date = firstDayOfWeek
        val modifier = Modifier.weight(1f)

        if (days == null) {
            for (i in 0..6) {
                DayItem(day = Day(date = date.plus(i, DateTimeUnit.DAY)), modifier)
            }
        } else {
            for (i in 0..6) {
                val filteredList = days.filter { it.date == date.plus(i, DateTimeUnit.DAY) }
                if (filteredList.size == 1) {
                    val day = filteredList[0]
                    DayItem(day = day, modifier)
                } else {
                    DayItem(day = Day(date = date.plus(i, DateTimeUnit.DAY)), modifier)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeek() {
    WeekItem(weekIndex = 3, firstDayOfWeek = LocalDate(2022, 5, 7), null)
}