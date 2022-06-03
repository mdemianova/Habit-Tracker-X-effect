package com.ignation.thexeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ignation.thexeffect.navigation.HabitNavigation
import com.ignation.thexeffect.ui.theme.TheXEffectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheXEffectTheme {
                // Scaffold here solves the launch bug
                // for Xiaomi devices.
                Scaffold {
                    HabitNavigation()
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