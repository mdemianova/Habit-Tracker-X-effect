package com.ignation.thexeffect

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ignation.thexeffect.database.BoardDao
import com.ignation.thexeffect.database.entities.Board
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "BoardViewModel"
@HiltViewModel
class BoardViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var boardDao: BoardDao

    fun createBoard() {
        viewModelScope.launch {
            boardDao.insertBoard(Board(isActive = true, title = "Test Board", startDate = 1L))
            Log.d(TAG, "createBoard: executed")
        }
    }
}