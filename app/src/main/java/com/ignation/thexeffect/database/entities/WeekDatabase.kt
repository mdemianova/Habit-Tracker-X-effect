package com.ignation.thexeffect.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Stores user comments for weeks.
 */
@Entity(tableName = "week_database")
data class WeekDatabase(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val boardId: Long,
    val index: Int,
    val comment: String? = null
)