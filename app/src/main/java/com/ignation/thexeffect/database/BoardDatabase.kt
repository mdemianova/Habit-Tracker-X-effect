package com.ignation.thexeffect.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ignation.thexeffect.model.Board
import com.ignation.thexeffect.model.Day

@Entity(tableName = "boards")
data class BoardDatabase(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val day: Day
)