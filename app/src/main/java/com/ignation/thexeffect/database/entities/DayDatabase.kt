package com.ignation.thexeffect.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table for storing successful days.
 */
@Entity(tableName = "day_database")
data class DayDatabase(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val boardId: Long,
    val date: Long,
)