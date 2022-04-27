package com.ignation.thexeffect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Stores user comments for weeks.
 */
@Entity(tableName = "week_database")
data class WeekEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val boardId: Long,
    val index: Int,
    val comment: String,
    val day_1: Boolean = false,
    val day_2: Boolean = false,
    val day_3: Boolean = false,
    val day_4: Boolean = false,
    val day_5: Boolean = false,
    val day_6: Boolean = false,
    val day_7: Boolean = false,
)