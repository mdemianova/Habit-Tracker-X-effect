package com.ignation.thexeffect.database

import androidx.room.*
import com.ignation.thexeffect.database.entities.Board
import com.ignation.thexeffect.database.entities.relations.BoardWithDays

@Dao
interface BoardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoard(board: Board)

    @Transaction
    @Query("SELECT * FROM board WHERE id = :id")
    suspend fun getBoardWithDays(id: Long): List<BoardWithDays>
}