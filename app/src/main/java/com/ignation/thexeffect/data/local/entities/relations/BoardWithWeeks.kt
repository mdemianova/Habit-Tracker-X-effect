package com.ignation.thexeffect.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ignation.thexeffect.data.local.entities.BoardEntity
import com.ignation.thexeffect.data.local.entities.WeekEntity

data class BoardWithWeeks(
    @Embedded val boardEntity: BoardEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "boardId"
    )
    val weekEntities: List<WeekEntity>
)