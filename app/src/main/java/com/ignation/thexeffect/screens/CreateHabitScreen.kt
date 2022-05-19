package com.ignation.thexeffect.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ignation.thexeffect.HabitViewModel
import com.ignation.thexeffect.R
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Week
import kotlinx.datetime.LocalDate
import java.util.*

@Composable
fun CreateHabitScreen(navController: NavController, habitViewModel: HabitViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Create a card") },
                navigationIcon = {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        }
                    )
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
            color = MaterialTheme.colors.background
        ) {
            CreateHabitContent(navController, habitViewModel)
        }
    }
}


@Composable
fun CreateHabitContent(navController: NavController, habitViewModel: HabitViewModel) {

    val title = remember {
        mutableStateOf("")
    }

    val startDate = remember {
        mutableStateOf(Calendar.getInstance())
    }

    val typeState = remember {
        mutableStateOf(true)
    }

    val weeksList = mutableMapOf<Int, Week>()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ChooseType(typeState)
            SetTitle(titleState = title)
            CreateDatePicker() { year, month, day ->
                startDate.value.set(year, month, day)
            }
            WeekDescription(weeksList)
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(
                    onClick = {
                        if (title.value.isNotEmpty()) {
                            habitViewModel.createHabit(
                                board = Board(
                                    title = title.value,
                                    isActive = true,
                                    startDate = LocalDate(
                                        year = startDate.value.get(Calendar.YEAR),
                                        monthNumber = startDate.value.get(Calendar.MONTH) + 1,
                                        dayOfMonth = startDate.value.get(Calendar.DAY_OF_MONTH)
                                    ),
                                    isCreateHabit = typeState.value,
                                ),
                                weeks = weeksList.values.toList()
                            )
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Create")
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SetTitle(
    titleState: MutableState<String>
) {
    val controller = LocalSoftwareKeyboardController.current
    val focus = LocalFocusManager.current
    Column() {
        OutlinedTextField(
            value = titleState.value,
            onValueChange = { titleState.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Card title") },
            placeholder = { Text("Make it as clear as possible") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focus.clearFocus()
                    controller?.hide()
                }
            )
        )
    }
}

@Composable
fun CreateDatePicker(
    calendarSet: (Int, Int, Int) -> Unit
) {
    val context = LocalContext.current

    val calendar = Calendar.getInstance()
    val myYear = calendar.get(Calendar.YEAR)
    val myMonth = calendar.get(Calendar.MONTH)
    val myDay = calendar.get(Calendar.DAY_OF_MONTH)

    val myDate = remember {
        mutableStateOf("")
    }

    val dialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            myDate.value = "$dayOfMonth.${month + 1}.$year"
            calendarSet(year, month, dayOfMonth)
        }, myYear, myMonth, myDay
    )

    Column(
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Text(text = "When do you want to start?")

        Row {
            Button(
                onClick = { dialog.show() }
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
                        contentDescription = "Calendar image"
                    )

                    Text(text = "Choose date", color = Color.White)
                }
            }
            if (myDate.value.isNotEmpty()) {
                Text(
                    text = "Starting at: ${myDate.value}",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun ChooseType(typeState: MutableState<Boolean>) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = true, onClick = { typeState.value = true })
        Text(text = "Create Habit")
        Spacer(modifier = Modifier.width(10.dp))

        RadioButton(selected = false, onClick = { typeState.value = false })
        Text(text = "Break Habit")
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WeekDescription(weeksList: MutableMap<Int, Week>) {
    val weekFieldsCountState = remember {
        mutableStateOf(1)
    }

    val controller = LocalSoftwareKeyboardController.current
    val focus = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(text = "You can add description for each week")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(
                onClick = {
                    if (weekFieldsCountState.value > 1) {
                        weeksList.remove(weekFieldsCountState.value)
                        weekFieldsCountState.value -= 1
                    } else {
                        weekFieldsCountState.value = 0
                    }
                },
                modifier = Modifier
                    .padding(end = 20.dp)
            ) {
                Icon(Icons.Default.Clear, contentDescription = "Delete Week")
                Text(text = "Delete")
            }
            OutlinedButton(
                onClick = {
                    if (weekFieldsCountState.value < 7) {
                        weekFieldsCountState.value += 1
                    } else {
                        weekFieldsCountState.value = 7
                    }
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add week")
                Text(text = "Add")
            }
        }

        for (i in 1..weekFieldsCountState.value) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                InputWeekField(i, weeksList, controller, focus)
            }
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputWeekField(
    weekNumber: Int,
    weeksList: MutableMap<Int, Week>,
    controller: SoftwareKeyboardController?,
    focus: FocusManager
) {
    val text = remember {
        mutableStateOf("")
    }

    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
            weeksList[weekNumber] = Week(weekNumber, text.value)
        },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Week №$weekNumber") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focus.clearFocus()
                controller?.hide()
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    WeekDescription(weeksList = mutableMapOf())
}