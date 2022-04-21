package com.ignation.thexeffect.domain.models

data class Board(
    val id: Long? = null,
    val title: String,
    val isActive: Boolean,
    val startDate: Long,
    val weeks: List<Week>? = null,
    val days: List<Day>? = null
)

