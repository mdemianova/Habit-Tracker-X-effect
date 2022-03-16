package com.ignation.thexeffect.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ignation.thexeffect.model.Board
import com.ignation.thexeffect.model.Day

@Entity
data class Week(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val boardId: Long,
    val index: Int
)