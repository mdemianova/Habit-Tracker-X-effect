package com.ignation.thexeffect.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ignation.thexeffect.database.entities.Board
import com.ignation.thexeffect.database.entities.Week

data class BoardWithWeeks(
    @Embedded val board: Board,
    @Relation(
        parentColumn = "id",
        entityColumn = "boardId"
    )
    val weeks: List<Week>
)