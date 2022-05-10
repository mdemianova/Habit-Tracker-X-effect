package com.ignation.thexeffect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

/**
 * Table for storing successful days.
 */
@Entity(tableName = "day_database")
data class DayEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val boardId: Long,
    val date: LocalDate,
)