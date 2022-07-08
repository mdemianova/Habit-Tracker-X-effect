package com.ignation.thexeffect.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ignation.thexeffect.R
import com.ignation.thexeffect.domain.models.Board
import com.ignation.thexeffect.domain.models.Week
import com.ignation.thexeffect.navigation.HabitScreens
import com.ignation.thexeffect.ui.theme.UnavailableDay
import kotlinx.datetime.LocalDate
import java.util.*

@Composable
fun CreateHabitScreen(
    navController: NavController,
    habitViewModel: HabitViewModel,
    cardId: Long = -1L,
    boards: State<List<Board>>,
    weeks: State<List<Week>>
) {

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
                .padding(8.dp)
        ) {
            CreateHabitContent(navController, habitViewModel, cardId, boards, weeks)
        }
    }
}

@Composable
fun CreateHabitContent(
    navController: NavController,
    habitViewModel: HabitViewModel,
    cardId: Long,
    boards: State<List<Board>>,
    weeks: State<List<Week>>
) {

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

    if (cardId > -1) {
        val board = boards.value.filter { it.id == cardId }[0]
        val week = weeks.value.filter { it.boardId == cardId }

        title.value = board.title

        typeState.value = board.isCreateHabit

        for (i in 0..week.lastIndex) {
            weeksList[i + 1] = week[i]
        }
    }

    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .verticalScroll(scrollState)
        ) {
            SetTitle(title)
            Spacer(modifier = Modifier.height(20.dp))
            ChooseType(typeState)
            if (cardId == -1L) {
                Spacer(modifier = Modifier.height(20.dp))
                CreateDatePicker() { year, month, day ->
                    startDate.value.set(year, month, day)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            WeekDescription(weeksList, cardId)
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                if (cardId > -1) {
                    Button(
                        onClick = {
                            habitViewModel.updateBoard(
                                board = Board(
                                    id = cardId,
                                    title = title.value,
                                    isActive = true,
                                    startDate = boards.value.filter { it.id == cardId }[0].startDate,
                                    isCreateHabit = typeState.value
                                ),
                                weeks = weeksList.values.toList()
                            )
                            navController.navigate(route = HabitScreens.TitleScreen.name)
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Update")
                    }
                } else {
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
            ),
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
fun CreateDatePicker(
    calendarSet: (Int, Int, Int) -> Unit,
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

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = UnavailableDay
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "When do you want to start?",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (myDate.value.isEmpty()) {
                Button(
                    onClick = { dialog.show() },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
                            contentDescription = "Calendar image"
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Set a date", color = Color.White)
                    }
                }
            } else {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = myDate.value,
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(onClick = { dialog.show() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
                            contentDescription = "Calendar image"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChooseType(
    typeState: MutableState<Boolean>
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = UnavailableDay
    ) {
        Column(
            modifier = Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp)
        ) {
            Text(
                text = "Do you want to create a new habit or break an old one?",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioBtnWithLabel(
                    text = "Create Habit",
                    selectedState = typeState.value,
                    onClick = { typeState.value = true }
                )
                Spacer(modifier = Modifier.width(10.dp))
                RadioBtnWithLabel(
                    text = "Break Habit",
                    selectedState = !typeState.value,
                    onClick = { typeState.value = false }
                )
            }
        }
    }
}

@Composable
fun RadioBtnWithLabel(
    text: String,
    selectedState: Boolean,
    onClick: () -> Unit
) {
    RadioButton(
        selected = selectedState,
        onClick = onClick
    )
    Text(
        text = text,
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WeekDescription(
    weeksList: MutableMap<Int, Week>,
    cardId: Long
) {
    val weekFieldsCountState = remember {
        mutableStateOf(if (weeksList.isEmpty()) 1 else weeksList.size)
    }

    val controller = LocalSoftwareKeyboardController.current
    val focus = LocalFocusManager.current

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = UnavailableDay
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "You can add description for each week",
                fontSize = 18.sp,
                fontWeight = FontWeight.W500
            )

            for (i in 1..weekFieldsCountState.value) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    InputWeekField(i, weeksList, controller, focus, cardId)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedButton(
                    onClick = {
                        if (weekFieldsCountState.value > 0) {
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
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputWeekField(
    weekNumber: Int,
    weeksList: MutableMap<Int, Week>,
    controller: SoftwareKeyboardController?,
    focus: FocusManager,
    cardId: Long
) {
    val boardId = if (cardId > -1) cardId else null
    val text = remember {
        mutableStateOf(
            weeksList[weekNumber]?.comment ?: ""
        )
    }

    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
            weeksList[weekNumber] =
                Week(boardId = boardId, index = weekNumber, comment = text.value)
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
    ChooseType(
        typeState = remember {
            mutableStateOf(true)
        })
}

@Preview(showBackground = true)
@Composable
fun PreviewDate() {
    CreateDatePicker(
        calendarSet = { _, _, _ -> }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewWeek() {
    WeekDescription(
        weeksList = mutableMapOf(),
        cardId = -1
    )
}