package com.ignation.thexeffect.data.repository

import com.ignation.thexeffect.data.local.HabitDatabase
import com.ignation.thexeffect.data.mapper.toBoardEntity
import com.ignation.thexeffect.data.mapper.toBoardWithWeeks
import com.ignation.thexeffect.data.mapper.toWeekEntityList
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.BoardWithWeeks
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

    override suspend fun getActiveHabits(): Flow<List<BoardWithWeeks>> {
        return dao.getBoardWithWeeks().map { list -> list.map { it.toBoardWithWeeks() } }
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

}
