package com.ignation.thexeffect.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.Board

@Composable
fun BoardComponent(board: Board) {
    Column {
        Text(
            text = board.title,
            modifier = Modifier.padding(start = 14.dp)
        )
        WeekComponent()
        WeekComponent()
        WeekComponent()
        WeekComponent()
        WeekComponent()
        WeekComponent()
        WeekComponent()
    }
}