package com.ignation.thexeffect

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Week
import com.ignation.thexeffect.ui.theme.TheXEffectTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HabitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.activeBoards.observe(this@MainActivity) {
            viewModel.count = it.size
        }

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
                                        "Third habit",
                                        isActive = true,
                                        startDate = 3L
                                    ),
                                    weeks = listOf(
                                        Week(2, "5 push-ups"),
                                        Week(4, "15 push-ups")
                                    )
                                )
                            }
                        }) {
                            Text("Create")
                        }

                        Row {
                            Button(onClick = {
                                Log.d("HabitMainActivity", "${viewModel.count}")
                            }) {
                                Text("Get")
                            }
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
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TheXEffectTheme {
        Greeting()
    }
}