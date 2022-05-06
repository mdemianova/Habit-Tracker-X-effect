package com.ignation.thexeffect.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Table for storing created and archived boards.
 */
@Entity(tableName = "board_database")
data class BoardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String,
    val isActive: Boolean,
    val startDate: Calendar
)