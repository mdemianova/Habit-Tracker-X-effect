package com.ignation.thexeffect.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ignation.thexeffect.database.entities.Board
import com.ignation.thexeffect.model.Day

@Dao
interface BoardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoard(board: Board)
}