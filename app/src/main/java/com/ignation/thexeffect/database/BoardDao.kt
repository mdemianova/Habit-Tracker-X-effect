package com.ignation.thexeffect.database

import androidx.room.*
import com.ignation.thexeffect.database.entities.BoardDatabase
import com.ignation.thexeffect.database.entities.DayDatabase
import com.ignation.thexeffect.database.entities.WeekDatabase
import com.ignation.thexeffect.database.entities.relations.BoardWithDays
import com.ignation.thexeffect.models.Board
import com.ignation.thexeffect.models.Week
import com.ignation.thexeffect.models.asDatabaseModel

@Dao
interface BoardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoard(boardDatabase: BoardDatabase): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeeks(weeks: List<WeekDatabase>)

    @Transaction
    suspend fun insertBoardWithWeeks(board: Board, weeks: List<Week>) {
        val boardId = insertBoard(board.asDatabaseModel())
        board.id = boardId
        val dbWeeks = weeks.asDatabaseModel(boardId)
        insertWeeks(dbWeeks)
    }

    @Transaction
    suspend fun deleteBoardWithInfo(boardDatabase: BoardDatabase) {
        deleteBoard(boardDatabase)
        deleteWeeks(boardDatabase.id!!)
        deleteDays(boardDatabase.id)
    }

    @Delete
    suspend fun deleteBoard(boardDatabase: BoardDatabase)

    @Query("DELETE FROM week_database WHERE boardId = :id")
    suspend fun deleteWeeks(id: Long)

    @Query("DELETE FROM day_database WHERE boardId = :id")
    suspend fun deleteDays(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: DayDatabase)

    @Delete
    suspend fun deleteDay(day: DayDatabase)

    @Transaction
    @Query("SELECT * FROM board_database WHERE id = :id")
    suspend fun getBoardWithDays(id: Long): List<BoardWithDays>
}