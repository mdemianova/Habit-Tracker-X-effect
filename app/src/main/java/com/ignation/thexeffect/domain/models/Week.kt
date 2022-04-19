package com.ignation.thexeffect.domain.models

import com.ignation.thexeffect.data.local.entities.WeekEntity

data class Week(
    val index: Int,
    val comment: String? = null
)

fun List<Week>.asDatabaseModel(boardId: Long): List<WeekEntity> {
    return map {
        WeekEntity(
            boardId = boardId,
            index = it.index,
            comment = it.comment
        )
    }
}