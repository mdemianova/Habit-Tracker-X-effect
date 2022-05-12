package com.ignation.thexeffect.domain.models

import kotlinx.datetime.daysUntil
import kotlin.math.ceil

data class Week(
    val index: Int,
    var comment: String
)

fun getCurrentWeekFromBoard(board: Board, currentWeekIndex: Int): Week? {

    return if (board.weeks.isNullOrEmpty()) {
        null
    } else {
        for (item in board.weeks!!) {
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