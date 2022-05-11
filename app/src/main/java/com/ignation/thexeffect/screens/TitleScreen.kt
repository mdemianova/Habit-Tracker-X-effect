package com.ignation.thexeffect.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.ignation.thexeffect.HabitViewModel
import com.ignation.thexeffect.components.BoardSmall
import com.ignation.thexeffect.domain.models.Board

@Composable
fun TitleScreen(
    viewModel: HabitViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {
    Scaffold {
        content()
    }
}

@Composable
fun TitleContent(
    boards: List<Board>
) {
    LazyColumn {
        items(items = boards) {
            BoardSmall(board = it)
        }
    }
}