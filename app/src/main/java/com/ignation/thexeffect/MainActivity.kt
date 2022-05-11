package com.ignation.thexeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ignation.thexeffect.components.BoardSmall
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Day
import com.ignation.thexeffect.domain.models.DayStatus
import com.ignation.thexeffect.domain.models.Week
import com.ignation.thexeffect.screens.CreateHabitContent
import com.ignation.thexeffect.screens.CreateHabitScreen
import com.ignation.thexeffect.ui.theme.TheXEffectTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.datetime.LocalDate

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HabitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheXEffectTheme {
                // A surface container using the 'background' color from the theme
                Surface {
//                    CreateHabitScreen {
//                        CreateHabitContent()
//                    }
                    BoardSmall(board = Board(
                        title = "Become a superhero",
                        isActive = true,
                        startDate = LocalDate(2022, 5, 10),
                        isCreateHabit = true,
                        weeks = listOf(
                            Week(2, "This is week 2"),
                            Week(3, "This is week 3")
                        ),
                        days = listOf(
                            Day(DayStatus.COMPLETED, LocalDate(2022, 5, 9))
                        )
                    ))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TheXEffectTheme {
        CreateHabitScreen {
            CreateHabitContent()
        }
    }
}