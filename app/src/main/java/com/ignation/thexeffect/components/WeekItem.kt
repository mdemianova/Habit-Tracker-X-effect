package com.ignation.thexeffect.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    days: List<Day>,
    boardId: Long,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit,
) {
    val date = firstDayOfWeek

    val listOfDays = MutableList(7) { Day(date = date) }

    for (i in 0..6) {
        if (days.isEmpty()) {
            listOfDays.set(
                index = i,
                element = Day(
                    boardId = boardId,
                    date = date.plus(i, DateTimeUnit.DAY)
                )
            )
        } else {
            val filteredList = days.filter { it.date == date.plus(i, DateTimeUnit.DAY) }
            if (filteredList.size == 1) {
                val day = filteredList[0]
                listOfDays.set(
                    index = i,
                    element = day
                )
            } else {
                listOfDays.set(
                    index = i,
                    element = Day(
                        boardId = boardId,
                        date = date.plus(i, DateTimeUnit.DAY)
                    )
                )
            }
        }
    }

    val dayState = remember {
        mutableStateOf(listOfDays)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "$weekIndex",
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
        val modifier = Modifier.weight(1f)

        for (i in 0..6) {
            DayItem(
                day = dayState.value[i],
                modifier = modifier,
                insertDay = insertDay,
                deleteDay = deleteDay
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeek() {
    WeekItem(
        weekIndex = 1,
        firstDayOfWeek = LocalDate(2022, 5, 25),
        listOf(),
        5,
        {},
        {}
    )
}