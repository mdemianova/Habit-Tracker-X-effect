package com.ignation.thexeffect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ignation.thexeffect.data.repository.HabitRepositoryImpl
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.Week
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val habitRepositoryImpl: HabitRepositoryImpl
) : ViewModel() {

    private val _activeBoards = MutableStateFlow<List<Board>>(emptyList())
    val activeBoards = _activeBoards.asStateFlow()

    private val _allWeeks = MutableStateFlow<List<Week>>(emptyList())
    val allWeeks = _allWeeks.asStateFlow()

    private val _allDays = MutableStateFlow<List<Day>>(emptyList())
    val allDays = _allDays.asStateFlow()

    private fun setBoards() {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepositoryImpl.getActiveHabits().distinctUntilChanged().collect {
                if (it.isNotEmpty()) {
                    _activeBoards.value = it
                } else {
                    _activeBoards.value = emptyList()
                }
            }
        }
    }

    private fun setDays() {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepositoryImpl.getAllDays().distinctUntilChanged().collect {
                if (it.isNotEmpty()) {
                    _allDays.value = it
                }
            }
        }
    }

    private fun setWeeks() {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepositoryImpl.getAllWeeks().distinctUntilChanged().collect {
                _allWeeks.value = it
            }
        }
    }

    init {
        setBoards()
        setWeeks()
        setDays()
    }

    fun createHabit(board: Board, weeks: List<Week>) = viewModelScope.launch {
        habitRepositoryImpl.createHabit(board, weeks)
    }

    fun deleteHabit(board: Board) = viewModelScope.launch {
        habitRepositoryImpl.deleteHabit(board)
    }

    fun insertDay(day: Day) = viewModelScope.launch {
        habitRepositoryImpl.insertDay(day)
    }

    fun deleteDay(day: Day) = viewModelScope.launch {
        habitRepositoryImpl.deleteDay(day)
    }

    fun updateBoard(board: Board, weeks: List<Week>) {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepositoryImpl.updateBoard(board, weeks)
        }
    }
}