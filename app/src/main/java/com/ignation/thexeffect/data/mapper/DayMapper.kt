package com.ignation.thexeffect.data.mapper

import com.ignation.thexeffect.data.local.entities.DayEntity
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.DayStatus

fun Day.toDayEntity(id: Long): DayEntity {
    return DayEntity(
        boardId = id,
        date = this.date
    )
}

fun DayEntity.toDay(): Day {
    return Day(
        boardId = this.boardId,
        status = DayStatus.COMPLETED,
        date = this.date
    )
}

fun List<Day>.toDayEntityList(id: Long): List<DayEntity> {
    return map {
        it.toDayEntity(id)
    }
}

fun List<DayEntity>.toDayList(): List<Day> {
    return map {
        it.toDay()
    }
}