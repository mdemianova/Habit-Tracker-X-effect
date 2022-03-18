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
        val dbWeeks = weeks.asDatabaseModel(boardId)
        insertWeeks(dbWeeks)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: DayDatabase)

    @Delete
    suspend fun deleteDay(day: DayDatabase)

    @Transaction
    @Query("SELECT * FROM boardDatabase WHERE id = :id")
    suspend fun getBoardWithDays(id: Long): List<BoardWithDays>
}