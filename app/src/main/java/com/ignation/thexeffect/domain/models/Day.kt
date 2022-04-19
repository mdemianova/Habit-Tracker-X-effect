package com.ignation.thexeffect.domain.models

data class Day(var status: DayStatus = DayStatus.AVAILABLE) {
    fun statusChange() {
        status = when (status) {
            DayStatus.COMPLETED -> DayStatus.MISSED
            DayStatus.MISSED -> DayStatus.COMPLETED
            DayStatus.AVAILABLE -> DayStatus.COMPLETED
            else -> return
        }
    }
}

enum class DayStatus {
    COMPLETED,
    MISSED,
    UNAVAILABLE,
    AVAILABLE
}

