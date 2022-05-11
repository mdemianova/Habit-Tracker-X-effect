package com.ignation.thexeffect.screens

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ignation.thexeffect.R
import java.util.*

@Composable
fun CreateHabitScreen(content: @Composable () -> Unit) {

    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "Create a card")
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(8.dp),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}


@Composable
fun CreateHabitContent() {

    val title = remember {
        mutableStateOf("")
    }

    val startDate = remember {
        mutableStateOf(Calendar.getInstance())
    }

    val typeState = remember {
        mutableStateOf(true)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            ChooseType(typeState)
            SetTitle(titleState = title)
            CreateDatePicker() { year, month, day ->
                startDate.value.set(year, month, day)
                Log.d("TAG", "CreateHabitScreen: ${startDate.value.timeInMillis}")
            }
            WeekDescription()
            Spacer(modifier = Modifier.height(30.dp))
            CreateHabitControls()
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
            label = { Text("Habit title") },
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

@Composable
fun WeekDescription() {
    val weekFieldsCountState = remember {
        mutableStateOf(1)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "You can add description for each week")
        for (i in 1..weekFieldsCountState.value) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                InputWeekField(i)
                IconButton(
                    onClick = {
                        if (weekFieldsCountState.value > 1) {
                            weekFieldsCountState.value -= 1
                        } else {
                            weekFieldsCountState.value = 0
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {
                    Icon(Icons.Rounded.Delete, "Delete")
                }
            }
        }

        Button(
            onClick = {
                if (weekFieldsCountState.value < 7) {
                    weekFieldsCountState.value += 1
                } else {
                    weekFieldsCountState.value = 7
                }
            },
            modifier = Modifier
                .align(Alignment.End)
        ) {
            Text(text = "Add week")
        }
    }
}

@Composable
fun CreateHabitControls() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Cancel")
        }
        Button(onClick = {})
        {
            Text(text = "Create")
        }
    }
}

@Composable
fun InputWeekField(weekNumber: Int) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        label = { Text(text = "Week №$weekNumber") }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateHabit() {
    CreateHabitScreen {
        CreateHabitContent()
    }
}