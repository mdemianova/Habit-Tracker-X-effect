package com.ignation.thexeffect.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.Week
import com.ignation.thexeffect.ui.theme.BreakColor
import com.ignation.thexeffect.ui.theme.CardBorder
import com.ignation.thexeffect.ui.theme.CreateColor
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

@Composable
fun BoardTitle(
    titleText: String,
    boardType: Boolean,
    boardId: Long? = null,
    clickable: Boolean,
    onItemClick: (Long) -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .clickable(clickable) { onItemClick(boardId!!) }
            .fillMaxWidth(),
        color = if (boardType) CreateColor else BreakColor,
        border = BorderStroke(2.dp, CardBorder)
    ) {
        Text(
            text = titleText,
            modifier = Modifier
                .padding(top = 5.dp, start = 5.dp, bottom = 4.dp)
        )
    }
}

@Composable
fun BoardComment(
    week: Week?
) {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (week != null) {
            Text(
                text = week.comment
            )
        }
    }
}

@Composable
fun BoardBodySmall(
    startDate: LocalDate,
    boardId: Long?,
    weekIndex: Int,
    days: List<Day>,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit
) {
    val firstDayOfWeek = startDate.plus((weekIndex - 1) * 7, DateTimeUnit.DAY)
    val startInFuture = weekIndex <= 0

    Surface(
        modifier = Modifier
            .padding(horizontal = 8.dp)
    ) {
        if (startInFuture) {
            WeekItem(
                weekIndex = 1,
                firstDayOfWeek = startDate,
                days = days,
                boardId = boardId!!,
                insertDay = insertDay,
                deleteDay = deleteDay
            )
        } else {
            WeekItem(
                weekIndex = weekIndex,
                firstDayOfWeek = firstDayOfWeek,
                days = days,
                boardId = boardId!!,
                insertDay = insertDay,
                deleteDay = deleteDay
            )
        }
    }
}

@Composable
fun BoardBodyFull(
    boardId: Long?,
    boardStartDate: LocalDate,
    boardDays: List<Day>,
    insertDay: (Day) -> Unit,
    deleteDay: (Day) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .padding(end = 6.dp)
    ) {

        var startDate = boardStartDate

        for (i in 1..7) {
            WeekItem(
                weekIndex = i,
                firstDayOfWeek = startDate,
                days = boardDays,
                boardId = boardId!!,
                insertDay = insertDay,
                deleteDay = deleteDay
            )
            startDate = startDate.plus(7, DateTimeUnit.DAY)
        }
    }
}