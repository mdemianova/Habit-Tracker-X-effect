package com.ignation.thexeffect.domain.models

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Day(
    var status: DayStatus = DayStatus.EMPTY,
    val date: LocalDate
)

fun Day.setActualStatus() {
    if (status == DayStatus.EMPTY) {
        val currentDay = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        when {
            date.compareTo(currentDay) == 0 -> status = DayStatus.AVAILABLE
            date.compareTo(currentDay) >= 1 -> status = DayStatus.UNAVAILABLE
            date.compareTo(currentDay) <= -1 -> status = DayStatus.MISSED
        }
    }
}

fun Day.statusChange() {
    status = when (status) {
        DayStatus.COMPLETED -> DayStatus.MISSED
        DayStatus.MISSED -> DayStatus.COMPLETED
        DayStatus.AVAILABLE -> DayStatus.COMPLETED
        else -> return
    }
}

/**
 * By default, today is EMPTY for changing status.
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

