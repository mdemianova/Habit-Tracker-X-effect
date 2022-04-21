package com.ignation.thexeffect

import androidx.lifecycle.ViewModel
import com.ignation.thexeffect.data.repository.HabitRepositoryImpl
import com.ignation.thexeffect.domain.models.Board
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HabitViewModel @Inject constructor(
    private val habitRepositoryImpl: HabitRepositoryImpl
) : ViewModel() {

    fun getActiveBoards(): List<Board> {

        return listOf()
    }

    fun createBoard() {

    }
}