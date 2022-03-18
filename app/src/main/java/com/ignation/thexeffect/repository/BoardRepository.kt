package com.ignation.thexeffect.repository

import com.ignation.thexeffect.database.BoardDao
import com.ignation.thexeffect.models.Board
import com.ignation.thexeffect.models.Week
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BoardRepository @Inject constructor(
    private val boardDao: BoardDao
) {
    suspend fun createBoard(board: Board, weeks: List<Week>) {
        withContext(Dispatchers.IO) {
            boardDao.insertBoardWithWeeks(board, weeks)
        }
    }


}