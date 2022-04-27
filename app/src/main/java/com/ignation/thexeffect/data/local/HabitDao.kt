package com.ignation.thexeffect.data.local

import androidx.room.*
import com.ignation.thexeffect.data.local.entities.BoardEntity
import com.ignation.thexeffect.data.local.entities.WeekEntity
import com.ignation.thexeffect.data.local.entities.relations.BoardWithWeeksData
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    // Getting
    @Query("SELECT * FROM board_database WHERE isActive = 1")
    fun getActiveBoards(): Flow<List<BoardEntity>>

    @Transaction
    @Query("SELECT * FROM board_database WHERE isActive = 1")
    suspend fun getBoardWithWeeks(): Flow<List<BoardWithWeeksData>>

    // Inserting and Updating
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoard(boardEntity: BoardEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeeks(weeks: List<WeekEntity>?)

    @Update
    suspend fun updateWeek(week: WeekEntity)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertDay(day: DayEntity)

//    @Update
//    suspend fun updateDay(day: DayEntity)

    // Deleting
    @Transaction
    suspend fun deleteHabit(boardEntity: BoardEntity) {
        deleteWeeks(boardEntity.id!!)
        //deleteDays(boardEntity.id)
        deleteBoard(boardEntity)
    }

    @Delete
    suspend fun deleteBoard(boardEntity: BoardEntity)

    @Query("DELETE FROM week_database WHERE boardId = :id")
    suspend fun deleteWeeks(id: Long)

//    @Query("DELETE FROM day_database WHERE boardId = :id")
//    suspend fun deleteDays(id: Long)
}