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
import com.example.fitmeappfinalllimplementationv1.data.entity.Goals
import com.example.fitmeappfinalllimplementationv1.data.entity.History
import com.example.fitmeappfinalllimplementationv1.viewModel.GoalViewModel
import com.example.fitmeappfinalllimplementationv1.viewModel.HistoryViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditGoalDialogGoalScreen(
    goalViewModel: GoalViewModel,
    selectedGoal: Goals?,
    //showDialog: Dialog,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val title = remember { mutableStateOf(selectedGoal?.goalsTitle ?: "") }
    val targetSteps = remember { mutableStateOf(selectedGoal?.targetSteps ?: 0) }
    val keyboardController = LocalSoftwareKeyboardController.current
    //val stepsCount = remember { mutableStateOf(selectedGoal?.stepsCount?: 0) }
    var showDialog by remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = { onDismiss.invoke() },
        title = { Text("Edit Goal") },
        confirmButton = {
            Button(
                onClick = {
                    selectedGoal?.let {
                        goalViewModel.updateGoal(
                            it.copy(goalsTitle = title.value, targetSteps = targetSteps.value
                            )
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
                        value = title.value,
                        onValueChange = { title.value = it },
                        label = { Text(text = "Enter Goals title") },
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
                    /*TextField(
                        value = stepsCount.value.toString(),
                        onValueChange = { stepsCount.value = it.toIntOrNull() ?: 0 },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = { Text(text = "Enter Steps") },
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),

                        modifier = Modifier.background(
                            color = MaterialTheme.colors.background,
                            shape = RectangleShape
                        )
                    )*/

                }
                Row()
                {
                    TextField(
                        value = targetSteps.value.toString(),
                        onValueChange = { targetSteps.value = it.toIntOrNull() ?: 0 },

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done),
                        label = { Text(text = "Enter TargetSteps") },
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

