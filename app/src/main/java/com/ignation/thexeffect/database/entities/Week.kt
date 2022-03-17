package com.ignation.thexeffect.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Stores user comments for weeks.
 */
@Entity
data class Week(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val boardId: Long,
    val index: Int,
    val comment: String
)