package com.example.fitmeappfinalllimplementationv1.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fitmeappfinalllimplementationv1.data.entity.History
import com.example.fitmeappfinalllimplementationv1.viewModel.HistoryViewModel
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditGoalDialogHistoryScreen(
    historyViewModel: HistoryViewModel,
    selectedGoal: History?,
    //showDialog: Dialog,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val title = remember { mutableStateOf(selectedGoal?.goalsTitle ?: "") }
    val targetSteps = remember { mutableStateOf(selectedGoal?.targetSteps ?: 0) }
    val stepsCount = remember { mutableStateOf(selectedGoal?.stepsCount?: 0) }
    val oldStepsCount = remember { mutableStateOf(selectedGoal?.stepsCount?: 0) }
    var showDialog by remember { mutableStateOf(false) }
    val dateFormatter = remember { DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val date= remember { mutableStateOf(selectedGoal?.goalsDate?: 0) }
    val month= remember { mutableStateOf(selectedGoal?.goalsMonth?:0)}
    val year= remember { mutableStateOf(selectedGoal?.goalsYear?:0)}
    val formattedGoalsDate = remember(date.value, month.value, year.value) {
        String.format("%02d/%02d/%02d", date.value, month.value, year.value % 100)


    }
    AlertDialog(
        onDismissRequest = { onDismiss.invoke() },
        title = { Text("Edit Goal") },
        confirmButton = {
            Button(
                onClick = {
                    val count: Int = stepsCount.value+oldStepsCount.value

                    selectedGoal?.let {
                        historyViewModel.updateGoal(
                            it.copy(
                                stepsCount = count)
                              )

                    }
                    onDismiss.invoke()
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss.invoke() }) {
                Text(text = "Cancel")
            }},
        text = {
            Column(modifier = Modifier.padding(10.dp, 10.dp)) {
                Row()
                {
                    TextField(

                        value = formattedGoalsDate,
                        onValueChange = { formattedGoalsDate },
                        label = { Text(text = "Date") },
                        readOnly = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done,),
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),


                        modifier = Modifier.background(
                            color = MaterialTheme.colors.background,
                            shape = RectangleShape
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {keyboardController?.hide()}
                        )

                    )
                }
                Row()
                {
                    TextField(
                        value = title.value,
                        onValueChange = { title.value = it },
                        label = { Text(text = "Enter Goals title") },
                        readOnly = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done),
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),


                        modifier = Modifier.background(
                            color = MaterialTheme.colors.background,
                            shape = RectangleShape
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {keyboardController?.hide()}
                        )

                    )
                }
                Row()
                {
                    val oldStepsCount = stepsCount.value
                    TextField(
                        value = stepsCount.value.toString(),
                        onValueChange = { stepsCount.value = it.toIntOrNull() ?: 0 },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done),
                        label = { Text(text = "Enter Steps") },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),

                        modifier = Modifier.background(
                            color = MaterialTheme.colors.background,
                            shape = RectangleShape
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {keyboardController?.hide()}
                        )

                    )

                }
                Row()
                {
                    TextField(
                        value = targetSteps.value.toString(),
                        onValueChange = { targetSteps.value = it.toIntOrNull() ?: 0 },

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done),
                        label = { Text(text = "Enter TargetSteps") },
                        readOnly = true,
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),

                        modifier = Modifier.background(
                            color = MaterialTheme.colors.background,
                            shape = RectangleShape
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {keyboardController?.hide()}
                        )

                    )

                }



            }
        }
            )
        }

