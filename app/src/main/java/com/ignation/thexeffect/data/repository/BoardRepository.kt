package com.ignation.thexeffect.data.repository

import com.ignation.thexeffect.data.local.BoardDao
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Week
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class BoardRepository @Inject constructor(
    private val boardDao: BoardDao
) {
    suspend fun createBoard(board: Board, weeks: List<Week>) {
        withContext(Dispatchers.IO) {
            boardDao.insertBoardWithWeeks(board, weeks)
        }
    }

    suspend fun deleteBoard(board: Board) {
        withContext(Dispatchers.IO) {
            boardDao.deleteBoardWithInfo(board.asDatabaseModel())
        }
    }

    fun getActualWeeks() {
        val date = Date().time
    }
}

fun main() {

}