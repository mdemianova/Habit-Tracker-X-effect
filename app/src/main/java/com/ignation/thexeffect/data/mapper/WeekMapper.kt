package com.ignation.thexeffect.data.mapper

import com.ignation.thexeffect.data.local.entities.WeekEntity
import com.ignation.thexeffect.domain.models.Week

fun Week.toWeekEntity(boardId: Long): WeekEntity {
    return WeekEntity(
        boardId = boardId,
        index = this.index,
        comment = this.comment
    )
}

fun List<Week>.toWeekEntityList(boardId: Long): List<WeekEntity> {
    return map {
        WeekEntity(
            boardId = boardId,
            index = it.index,
            comment = it.comment
        )
    }
}

fun WeekEntity.toWeek(): Week {
    return Week(
        index = this.index,
        comment = this.comment
    )
}