package com.ignation.thexeffect.data.mapper

import com.ignation.thexeffect.data.local.entities.BoardEntity
import com.ignation.thexeffect.data.local.entities.DayEntity
import com.ignation.thexeffect.data.local.entities.WeekEntity
import com.ignation.thexeffect.domain.models.Board

fun Board.toBoardEntity(): BoardEntity {
    return BoardEntity(
        title = this.title,
        isActive = this.isActive,
        startDate = this.startDate
    )
}

fun BoardEntity.toBoard(weeks: List<WeekEntity>?, days: List<DayEntity>?): Board {
    return Board(
        id = this.id,
        title = this.title,
        isActive = this.isActive,
        startDate = this.startDate,
        weeks = weeks?.toWeekList(),
        days = days?.toDayList()
    )
}