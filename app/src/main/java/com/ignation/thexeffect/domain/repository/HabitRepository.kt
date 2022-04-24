package com.ignation.thexeffect.domain.repository

import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Week
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    suspend fun createHabit(board: Board, weeks: List<Week>?)

    suspend fun getActiveHabits(): Flow<List<Board>>

    suspend fun deleteHabit(board: Board)
}