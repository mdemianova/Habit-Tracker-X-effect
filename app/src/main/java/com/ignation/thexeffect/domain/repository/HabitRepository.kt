package com.ignation.thexeffect.domain.repository

import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.BoardWithWeeks
import com.ignation.thexeffect.domain.models.Week
import kotlinx.coroutines.flow.Flow

interface HabitRepository {

    suspend fun getActiveHabits(): Flow<List<BoardWithWeeks>>

    suspend fun createHabit(board: Board, weeks: List<Week>?)

    suspend fun deleteHabit(board: Board)
}