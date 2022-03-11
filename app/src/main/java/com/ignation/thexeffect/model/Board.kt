package com.ignation.thexeffect.model

data class Board(
    val title: String,
    val weekOne: Week = Week(),
    val weekTwo: Week = Week(index = 2),
    val weekThree: Week = Week(index = 3),
    val weekFour: Week = Week(index = 4),
    val weekFive: Week = Week(index = 5),
    val weekSix: Week = Week(index = 6),
    val weekSeven: Week = Week(index = 7)
)
