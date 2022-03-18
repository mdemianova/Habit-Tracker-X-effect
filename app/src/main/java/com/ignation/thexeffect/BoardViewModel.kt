package com.ignation.thexeffect

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ignation.thexeffect.models.Board
import com.ignation.thexeffect.models.Week
import com.ignation.thexeffect.repository.BoardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "BoardViewModel"
@HiltViewModel
class BoardViewModel @Inject constructor(
    private val boardRepository: BoardRepository
) : ViewModel() {

    init {
        Log.d(TAG, "ViewModel: created")
    }

    fun createBoard() {
        viewModelScope.launch {
            boardRepository.createBoard(
                Board("Title 1", true, 11L),
                listOf(Week(1), Week(2), Week(3))
            )
        }
    }
}