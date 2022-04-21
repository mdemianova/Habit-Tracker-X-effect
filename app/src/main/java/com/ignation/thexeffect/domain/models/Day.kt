package com.ignation.thexeffect.domain.models

data class Day(
    var status: DayStatus = DayStatus.AVAILABLE,
    val date: Long
)

fun Day.statusChange() {
    status = when (status) {
        DayStatus.COMPLETED -> DayStatus.MISSED
        DayStatus.MISSED -> DayStatus.COMPLETED
        DayStatus.AVAILABLE -> DayStatus.COMPLETED
        else -> return
    }
}

/**
 * By default, today is AVAILABLE for changing status.
 * Future days are UNAVAILABLE.
 * COMPLETED days are saved and loaded from database.
 * Days in past that are not completed, are MISSED.
 */
enum class DayStatus {
    COMPLETED,
    MISSED,
    UNAVAILABLE,
    AVAILABLE
}

