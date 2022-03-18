package com.ignation.thexeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ignation.thexeffect.models.Board
import com.ignation.thexeffect.ui.components.BoardComponent
import com.ignation.thexeffect.ui.theme.TheXEffectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: BoardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheXEffectTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    Button(
                        onClick = { viewModel.createBoard() }

                    ) {
                        Text("Add Board in DB")
                    }
                    //Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column {
        BoardComponent(board = Board("Test board", true, 2L))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TheXEffectTheme {
        Greeting()
    }
}