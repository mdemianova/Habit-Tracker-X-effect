package com.ignation.thexeffect.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ignation.thexeffect.database.entities.Board
import com.ignation.thexeffect.database.entities.Day
import com.ignation.thexeffect.database.entities.Week

data class BoardWithDays(
    @Embedded val board: Board,
    @Relation(
        parentColumn = "id",
        entityColumn = "board_id"
    )
    val days: List<Day>
)