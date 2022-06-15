package com.ignation.thexeffect.domain.models

import com.ignation.thexeffect.utils.CurrentDate
import kotlinx.datetime.LocalDate

data class Day(
    var boardId: Long? = null,
    var status: DayStatus = DayStatus.EMPTY,
    val date: LocalDate,
)

fun Day.setActualStatus(currentDay: LocalDate = CurrentDate.now) {
    if (status == DayStatus.EMPTY) {
        when {
            date.compareTo(currentDay) == 0 -> status = DayStatus.AVAILABLE
            date.compareTo(currentDay) >= 1 -> status = DayStatus.UNAVAILABLE
            date.compareTo(currentDay) <= -1 -> status = DayStatus.MISSED
        }
    }
}

/**
 * By default, non-saved days are EMPTY.
 * Today is AVAILABLE.
 * Future days are UNAVAILABLE.
 * COMPLETED days are saved and loaded from database.
 * Days in past that are not completed, are MISSED.
 */
enum class DayStatus {
    EMPTY,
    COMPLETED,
    MISSED,
    UNAVAILABLE,
    AVAILABLE
}