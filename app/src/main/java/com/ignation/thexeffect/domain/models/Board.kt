package com.ignation.thexeffect.domain.models

import kotlinx.datetime.LocalDate

data class Board(
    val id: Long? = null,
    val title: String,
    val isActive: Boolean,
    val startDate: LocalDate,
    val isCreateHabit: Boolean,
    var weeks: List<Week>? = null,
    var days: List<Day>? = null
)

