package com.ignation.thexeffect.domain.models

import com.ignation.thexeffect.utils.CurrentDate
import kotlinx.datetime.daysUntil
import kotlin.math.ceil

data class Week(
    val boardId: Long? = null,
    val index: Int,
    var comment: String
)

fun getCurrentWeek(weeks: List<Week>?, currentWeekIndex: Int): Week? {

    return if (weeks.isNullOrEmpty()) {
        null
    } else {
        for (item in weeks) {
            if (item.index == currentWeekIndex) return item
        }
        null
    }
}

fun getCurrentWeekIndex(board: Board): Int {
    val currentDate = CurrentDate.now
    val startDate = board.startDate
    val indexOfCurrentDayInBoard = startDate.daysUntil(currentDate) + 1

    return ceil(indexOfCurrentDayInBoard / 7f).toInt()
}