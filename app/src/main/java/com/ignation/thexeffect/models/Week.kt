package com.ignation.thexeffect.models

import com.ignation.thexeffect.database.entities.WeekDatabase

data class Week(
    val index: Int,
    val comment: String? = null
)

fun List<Week>.asDatabaseModel(boardId: Long): List<WeekDatabase> {
    return map {
        WeekDatabase(
            boardId = boardId,
            index = it.index,
            comment = it.comment
        )
    }
}