package com.ignation.thexeffect.data.repository

import com.ignation.thexeffect.data.local.HabitDao
import com.ignation.thexeffect.data.mapper.*
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.Week
import com.ignation.thexeffect.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HabitRepositoryImpl @Inject constructor(
    private val dao: HabitDao
) : HabitRepository {

    override fun getActiveHabits(): Flow<List<Board>> {
        return dao.getActiveBoards().map { it.toBoardList() }
    }

    override fun getAllWeeks(): Flow<List<Week>> {
        return dao.getAllWeeks().map { it.toWeekList() }
    }

    override fun getAllDays(): Flow<List<Day>> {
        return dao.getAllDays().map { it.toDayList() }
    }

    override suspend fun createHabit(board: Board, weeks: List<Week>) {
        val id = dao.insertBoard(board.toBoardEntity())
        if (weeks.isNotEmpty()) {
            dao.insertWeeks(weeks.toWeekEntityList(id))
        }
    }

    override suspend fun deleteHabit(board: Board) {
        dao.deleteHabit(board.toBoardEntity())
    }

    override suspend fun insertDay(day: Day) {
        dao.insertDay(day.toDayEntity())
    }

    override suspend fun deleteDay(day: Day) {
        dao.deleteDay(day.toDayEntity().boardId, day.toDayEntity().date)
    }

    override suspend fun updateBoard(board: Board, weeks: List<Week>) {
        dao.updateBoard(board.toBoardEntity())
        dao.deleteWeeks(board.id!!)
        dao.insertWeeks(weeks.toWeekEntityList(board.id))
    }
}
