package com.ignation.thexeffect.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ignation.thexeffect.database.entities.BoardDatabase
import com.ignation.thexeffect.database.entities.DayDatabase

data class BoardWithDays(
    @Embedded val boardDatabase: BoardDatabase,
    @Relation(
        parentColumn = "id",
        entityColumn = "boardId"
    )
    val dayDatabases: List<DayDatabase>
)