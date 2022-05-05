package com.ignation.thexeffect.domain.models

import java.util.*

data class Board(
    val id: Long? = null,
    val title: String,
    val isActive: Boolean,
    val startDate: Calendar,
    var weeks: List<Week>? = null,
    var days: List<Day>? = null
)

