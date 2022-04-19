package com.ignation.thexeffect.domain.models

data class Board(
    var id: Long? = null,
    val title: String,
    val isActive: Boolean,
    val startDate: Long
)

