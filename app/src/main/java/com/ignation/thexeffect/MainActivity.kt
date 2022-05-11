package com.ignation.thexeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ignation.thexeffect.navigation.HabitNavigation
import com.ignation.thexeffect.ui.theme.TheXEffectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HabitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheXEffectTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    HabitNavigation()
                    /*
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
                    )) */
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TheXEffectTheme {
        HabitNavigation()
    }
}