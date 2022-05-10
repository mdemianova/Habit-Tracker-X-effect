package com.ignation.thexeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ignation.thexeffect.components.WeekItem
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
                    WeekItem(firstDayOfWeek = LocalDate(2022, 5, 7))
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