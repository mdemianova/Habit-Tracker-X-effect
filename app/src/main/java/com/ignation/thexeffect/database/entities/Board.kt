package com.ignation.thexeffect.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Table for storing created and archived boards.
 */
@Entity
data class Board(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val title: String,
    val isActive: Boolean,
    val startDate: Long
)