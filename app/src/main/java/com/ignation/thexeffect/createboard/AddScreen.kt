package com.ignation.thexeffect.createboard

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable

@Composable
fun AddScreen(
    onAddTitle: () -> Unit
) {
    Column {
        Text(
            text = "Board title"
        )
        TextField(
            value = "",
            onValueChange = {}
        )
    }
}

