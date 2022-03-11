package com.ignation.thexeffect.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.model.Board

@Composable
fun BoardComponent(board: Board) {
    Column {
        Text(
            text = board.title,
            modifier = Modifier.padding(start = 14.dp)
        )
        WeekComponent(week = board.weekOne)
        WeekComponent(week = board.weekTwo)
        WeekComponent(week = board.weekThree)
        WeekComponent(week = board.weekFour)
        WeekComponent(week = board.weekFive)
        WeekComponent(week = board.weekSix)
        WeekComponent(week = board.weekSeven)
    }
}