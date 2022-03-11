package com.ignation.thexeffect.model

data class Week(
    val index: Int = 1,
    val dayOne: Day = Day(),
    val dayTwo: Day = Day(),
    val dayThree: Day = Day(),
    val dayFour: Day = Day(),
    val dayFive: Day = Day(),
    val daySix: Day = Day(),
    val daySeven: Day = Day(),
)