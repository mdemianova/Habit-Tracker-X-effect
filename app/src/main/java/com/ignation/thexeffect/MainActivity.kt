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
import androidx.lifecycle.lifecycleScope
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.ui.theme.TheXEffectTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HabitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheXEffectTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    Column() {
                        Button(onClick = {
                            lifecycleScope.launch {
                                viewModel.createHabit(
                                    Board(
                                        null,
                                        "First habit",
                                        isActive = true,
                                        startDate = 1L
                                    ),
                                    weeks = null
                                )
                            }
                        }) {
                            Text("Create")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    Column {
        //BoardComponent(board = Board(title = "Test board", isActive = true, startDate = 2L))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TheXEffectTheme {
        Greeting()
    }
}