package com.ignation.thexeffect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table for storing successful days.
 */
@Entity(tableName = "day_database")
data class DayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val boardId: Long,
    val date: Long,
)