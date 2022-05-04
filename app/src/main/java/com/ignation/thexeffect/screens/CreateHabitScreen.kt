package com.ignation.thexeffect.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
fun CreateHabitScreen() {
    val title = remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFFfdfdf5),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column {
            SetTitle(stateValue = title)
            CreateDatePicker()
        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SetTitle(
    stateValue: MutableState<String>
) {
    val controller = LocalSoftwareKeyboardController.current
    val focus = LocalFocusManager.current
    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            value = stateValue.value,
            onValueChange = { stateValue.value = it },
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
fun CreateDatePicker() {

    val context = LocalContext.current

    val myYear: Int
    val myMonth: Int
    val myDay: Int

    val calendar = Calendar.getInstance()

    myYear = calendar.get(Calendar.YEAR)
    myMonth = calendar.get(Calendar.MONTH)
    myDay = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val myDate = remember {
        mutableStateOf("")
    }

    val dialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            myDate.value = "$dayOfMonth.${month + 1}.$year"
        }, myYear, myMonth, myDay
    )

    Column {
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
                    Text(text = "Set date", color = Color.White)
                }
            }

            Text(
                text = "Start Date: ${myDate.value}"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreateHabit() {
    CreateHabitScreen()
}