package com.ignation.thexeffect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ignation.thexeffect.data.repository.BoardRepository
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Week
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "BoardViewModel"

@HiltViewModel
class BoardViewModel @Inject constructor(
    private val boardRepository: BoardRepository
) : ViewModel() {

    fun getActiveBoards(): List<Board> {



        return listOf()
    }

    fun createBoard() {
        viewModelScope.launch {
            boardRepository.createBoard(
                Board(title = "Title 1", isActive = true, startDate = 11L),
                listOf(Week(1), Week(2), Week(3))
            )
        }
    }
}