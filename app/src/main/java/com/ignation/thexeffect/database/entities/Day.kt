package com.ignation.thexeffect.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ignation.thexeffect.model.Board
import com.ignation.thexeffect.model.Day

@Entity
data class Day(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val board_id: Long,
    val date: Long,
    val is_checked: Boolean
)