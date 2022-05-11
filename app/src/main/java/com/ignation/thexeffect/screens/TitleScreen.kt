package com.ignation.thexeffect.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ignation.thexeffect.HabitViewModel
import com.ignation.thexeffect.navigation.HabitScreens

@Composable
fun TitleScreen(navController: NavController) {
    val viewModel: HabitViewModel = hiltViewModel()
    Scaffold(
        topBar = {
            Text(text = "Title")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(route = HabitScreens.CreateHabitScreen.name)
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add new Card")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        TitleContent()
    }
}

@Composable
fun TitleContent(
    //boards: List<Board>
) {

    Text(text = "Title Screen")
//    LazyColumn {
//        items(items = boards) {
//            BoardSmall(board = it)
//        }
//    }
}