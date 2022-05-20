package com.ignation.thexeffect.domain.repository

import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.Week
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    fun getActiveHabits(): Flow<List<Board>>

    fun getAllWeeks(): Flow<List<Week>>

    fun getAllDays(): Flow<List<Day>>

    suspend fun createHabit(board: Board, weeks: List<Week>?)

    suspend fun deleteHabit(board: Board)

    suspend fun insertDay(boardId: Long, day: Day)
}