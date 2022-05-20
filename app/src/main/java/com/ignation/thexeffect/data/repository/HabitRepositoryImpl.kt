package com.ignation.thexeffect.data.repository

import com.ignation.thexeffect.data.local.HabitDatabase
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
    private val db: HabitDatabase
) : HabitRepository {

    private val dao = db.habitDao()

    override fun getActiveHabits(): Flow<List<Board>> {
        return dao.getActiveBoards().map { it.toBoardList() }
    }

    override fun getAllWeeks(): Flow<List<Week>> {
        return dao.getAllWeeks().map { it.toWeekList() }
    }

    override fun getAllDays(): Flow<List<Day>> {
        return dao.getAllDays().map { it.toDayList() }
    }

    override suspend fun createHabit(board: Board, weeks: List<Week>?) {
        val id = dao.insertBoard(board.toBoardEntity())
        if (weeks != null) {
            dao.insertWeeks(weeks.toWeekEntityList(id))
        }
    }

    override suspend fun deleteHabit(board: Board) {
        db.habitDao().deleteHabit(board.toBoardEntity())
    }

    override suspend fun insertDay(boardId: Long, day: Day) {
        db.habitDao().insertDay(day.toDayEntity(boardId))
    }
}
