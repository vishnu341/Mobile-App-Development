package com.example.fitmeappfinalllimplementationv1.presentation.components

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fitmeappfinalllimplementationv1.data.entity.History
import com.example.fitmeappfinalllimplementationv1.viewModel.HistoryViewModel
import kotlinx.coroutines.launch
import java.util.*
import kotlin.random.Random

@OptIn(ExperimentalComposeUiApi::class)
@Composable

fun AddHistoryData(
    historyViewModel: HistoryViewModel,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val goalsTitle = remember { mutableStateOf("") }
    val targetSteps = remember { mutableStateOf(0) }
    val stepsCount=remember { mutableStateOf(0) }
    val goalsDate = remember { mutableStateOf(0) }
    val goalsMonth = remember { mutableStateOf(0) }
    val goalsYear = remember { mutableStateOf(0) }
    val uniqueId = remember { mutableStateOf("") }


    val keyboardController = LocalSoftwareKeyboardController.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val scope = rememberCoroutineScope()
    var error by remember { mutableStateOf<String?>(null) }
    AlertDialog(
        onDismissRequest = { onDismiss.invoke() },
        title = { Text(text = "Add Goal") },
        text={

            Column(modifier = Modifier.padding(10.dp, 10.dp))
            {
                Row()
                {TextField(
                    value = goalsTitle.value,
                    onValueChange = { goalsTitle.value = it  },
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



                    )}

                Row()
                {TextField(
                    value = targetSteps.value.toString(),
                    onValueChange = { targetSteps.value = it.toIntOrNull() ?: 0  },

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

                Row()
                {TextField(
                    value = stepsCount.value.toString(),
                    onValueChange = { stepsCount.value = it.toIntOrNull() ?: 0  },

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number
                    , imeAction = ImeAction.Done),
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
                    Box(
                        modifier = Modifier.weight(1f)
                            .padding(end = 4.dp)
                    ){
                        TextField(
                            value = goalsDate.value.toString(),
                            onValueChange = { goalsDate.value = it.toIntOrNull() ?: 0 },

                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done),
                            label = { Text(text = "DD") },
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
                    Box(
                        modifier = Modifier.weight(1f)
                            .padding(start = 4.dp, end = 4.dp)
                    ){
                        TextField(
                            value = goalsMonth.value.toString(),
                            onValueChange = { input ->
                                goalsMonth.value = input.toIntOrNull() ?: 0
                                val regex = "^(1[0-2]|[1-9])$".toRegex()
                                error = if (!input.matches(regex)) {
                                    "Invalid month format"
                                } else if (input.toInt() !in 1..12) {
                                    "Month must be between 1 and 12"
                                } else {
                                    null
                                }
                            },
                            label = { Text("Month") },
                            isError = error != null,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done),
                            keyboardActions = KeyboardActions(
                                onDone = {keyboardController?.hide()}
                            )
                        )
                    }
                    Box(
                        modifier = Modifier.weight(1f)
                            .padding(start = 4.dp)
                    ) {
                        TextField(
                            value = goalsYear.value.toString(),
                            onValueChange = { goalsYear.value = it.toIntOrNull() ?: 0 },

                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done),
                            label = { Text(text = "YYYY") },
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



        },
        confirmButton = {
            Button(
                onClick = {

                    val history = History(
                        goalsId = Random.nextInt(1, Int.MAX_VALUE),
                        goalsTitle = goalsTitle.value,
                        targetSteps = targetSteps.value,
                        stepsCount = stepsCount.value,
                        goalsDate = goalsDate.value,
                        goalsMonth = goalsMonth.value,
                        goalsYear = goalsYear.value,

                        uniqueId = UUID.randomUUID().toString()


                    )
                    scope.launch {
                        val isDataExists = historyViewModel.checkRecordExistforDate(goalsDate.value)
                        Log.d("isDataExists", isDataExists.toString())
                        if (!(isDataExists)) {
                            historyViewModel.insertHistoryData(history)
                            Toast.makeText(context, "Record added successfully.", Toast.LENGTH_SHORT)
                                .show()
                            onDismiss.invoke()

                        } else {
                            Toast.makeText(
                                context,
                                "Error adding record!! Record for that date exist",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }


                }

            )
                    {
                Text("Add")

            }

        },
        dismissButton = {
            Button(onClick = { onDismiss.invoke() }) {
                Text("Cancel")
            }
        }
    )
}



