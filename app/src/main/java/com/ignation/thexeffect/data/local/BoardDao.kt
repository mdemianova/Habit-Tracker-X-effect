package com.ignation.thexeffect.data.local

import androidx.room.*
import com.ignation.thexeffect.data.local.entities.BoardEntity
import com.ignation.thexeffect.data.local.entities.DayEntity
import com.ignation.thexeffect.data.local.entities.WeekEntity
import com.ignation.thexeffect.data.local.entities.relations.BoardWithDays
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Week
import com.ignation.thexeffect.domain.models.asDatabaseModel

@Dao
interface BoardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoard(boardEntity: BoardEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeeks(weeks: List<WeekEntity>)

    @Transaction
    suspend fun insertBoardWithWeeks(board: Board, weeks: List<Week>) {
        val boardId = insertBoard(board.asDatabaseModel())
        board.id = boardId
        val dbWeeks = weeks.asDatabaseModel(boardId)
        insertWeeks(dbWeeks)
    }

    @Transaction
    suspend fun deleteBoardWithInfo(boardEntity: BoardEntity) {
        deleteBoard(boardEntity)
        deleteWeeks(boardEntity.id!!)
        deleteDays(boardEntity.id)
    }

    @Delete
    suspend fun deleteBoard(boardEntity: BoardEntity)

    @Query("DELETE FROM week_database WHERE boardId = :id")
    suspend fun deleteWeeks(id: Long)

    @Query("DELETE FROM day_database WHERE boardId = :id")
    suspend fun deleteDays(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: DayEntity)

    @Delete
    suspend fun deleteDay(day: DayEntity)

    @Transaction
    @Query("SELECT * FROM board_database WHERE id = :id")
    suspend fun getBoardWithDays(id: Long): List<BoardWithDays>
}