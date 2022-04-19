package com.ignation.thexeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ignation.thexeffect.createboard.AddBoardViewModel
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.ui.components.BoardComponent
import com.ignation.thexeffect.ui.theme.TheXEffectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AddBoardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheXEffectTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    //AddScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column {
        BoardComponent(board = Board(title = "Test board", isActive = true, startDate = 2L))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TheXEffectTheme {
        Greeting()
    }
}