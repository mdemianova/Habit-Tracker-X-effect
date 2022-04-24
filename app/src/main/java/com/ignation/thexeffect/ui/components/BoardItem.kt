package com.ignation.thexeffect.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.domain.models.Board

@Composable
fun BoardItem(
    board: Board,
    modifier: Modifier
) {
    Column {
        Text(
            text = board.title,
            modifier = modifier.padding(start = 14.dp)
        )

        for (i in 1..7) {
            WeekItem()
        }
    }
}