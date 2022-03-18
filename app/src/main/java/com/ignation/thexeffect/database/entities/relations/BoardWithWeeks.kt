package com.ignation.thexeffect.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ignation.thexeffect.database.entities.BoardDatabase
import com.ignation.thexeffect.database.entities.WeekDatabase

data class BoardWithWeeks(
    @Embedded val boardDatabase: BoardDatabase,
    @Relation(
        parentColumn = "id",
        entityColumn = "boardId"
    )
    val weekDatabases: List<WeekDatabase>
)