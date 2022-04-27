package com.ignation.thexeffect

import androidx.lifecycle.ViewModel
import com.ignation.thexeffect.data.repository.HabitRepositoryImpl
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Week
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val habitRepositoryImpl: HabitRepositoryImpl
) : ViewModel() {

    suspend fun createHabit(board: Board, weeks: List<Week>?) {
        habitRepositoryImpl.createHabit(board, weeks)
    }
}