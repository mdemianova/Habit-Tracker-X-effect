package com.ignation.thexeffect.data.local

import androidx.room.*
import com.ignation.thexeffect.data.local.entities.BoardEntity
import com.ignation.thexeffect.data.local.entities.DayEntity
import com.ignation.thexeffect.data.local.entities.WeekEntity
import com.ignation.thexeffect.data.local.entities.relations.BoardWithDays

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoard(boardEntity: BoardEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeeks(weeks: List<WeekEntity>)

    @Transaction
    suspend fun deleteHabit(boardEntity: BoardEntity) {
        val id = boardEntity.id!!
        deleteBoard(boardEntity)
        deleteWeeks(id)
        deleteDays(id)
    }

    @Delete
    suspend fun deleteBoard(boardEntity: BoardEntity)

    @Query("DELETE FROM week_database WHERE boardId = :id")
    suspend fun deleteWeeks(id: Long)

    @Query("DELETE FROM day_database WHERE boardId = :id")
    suspend fun deleteDays(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: DayEntity)

    @Update
    suspend fun updateDay(day: DayEntity)

    @Transaction
    @Query("SELECT * FROM board_database WHERE id = :id")
    suspend fun getBoardWithDays(id: Long): List<BoardWithDays>
}