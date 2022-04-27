package com.ignation.thexeffect.domain.models

data class Board(
    val id: Long? = null,
    val title: String,
    val isActive: Boolean,
    val startDate: Long,
    var weeks: List<Week>? = null,
    var days: List<Day>? = null
)

