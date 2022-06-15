package com.ignation.thexeffect.data.local

import androidx.room.*
import com.ignation.thexeffect.data.local.entities.BoardEntity
import com.ignation.thexeffect.data.local.entities.DayEntity
import com.ignation.thexeffect.data.local.entities.WeekEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

@Dao
interface HabitDao {

    // Getting
    @Query("SELECT * FROM board_database WHERE isActive = 1")
    fun getActiveBoards(): Flow<List<BoardEntity>>

    @Query("SELECT * FROM board_database WHERE id = :id")
    fun getBoardById(id: Long): BoardEntity

    @Query("SELECT * FROM week_database")
    fun getAllWeeks(): Flow<List<WeekEntity>>

    @Query("SELECT * FROM day_database")
    fun getAllDays(): Flow<List<DayEntity>>

    @Query("SELECT * FROM day_database WHERE boardId = :boardId")
    fun getBoardDays(boardId: Long): Flow<List<DayEntity>>

    // Inserting and Updating
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoard(boardEntity: BoardEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeeks(weeks: List<WeekEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: DayEntity)

    @Update
    suspend fun updateBoard(board: BoardEntity)

    // Deleting
    @Transaction
    suspend fun deleteHabit(boardEntity: BoardEntity) {
        deleteWeeks(boardEntity.id!!)
        deleteDays(boardEntity.id)
        deleteBoard(boardEntity)
    }

    @Delete
    suspend fun deleteBoard(boardEntity: BoardEntity)

    @Query("DELETE FROM week_database WHERE boardId = :id")
    suspend fun deleteWeeks(id: Long)

    @Query("DELETE FROM week_database WHERE boardId = :boardId AND `index` = :index")
    suspend fun deleteWeek(boardId: Long, index: Int)

    @Query("DELETE FROM day_database WHERE boardId = :id")
    suspend fun deleteDays(id: Long)

    @Query("DELETE FROM day_database WHERE boardId = :boardId AND date = :date")
    suspend fun deleteDay(boardId: Long, date: LocalDate)
}