package com.ignation.thexeffect.domain.models

data class BoardWithWeeks(
    val board: Board,
    var weeks: List<Week>
)
