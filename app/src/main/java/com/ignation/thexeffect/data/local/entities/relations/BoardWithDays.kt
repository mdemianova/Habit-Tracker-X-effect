package com.ignation.thexeffect.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ignation.thexeffect.data.local.entities.BoardEntity
import com.ignation.thexeffect.data.local.entities.DayEntity

data class BoardWithDays(
    @Embedded val boardEntity: BoardEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "boardId"
    )
    val dayEntities: List<DayEntity>
)