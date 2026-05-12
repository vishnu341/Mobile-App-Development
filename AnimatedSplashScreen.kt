package com.example.fitmeappfinalllimplementationv1.presentation.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteAllHistoryAlertDialog(
    showDialog: Boolean,
    onConfirmDelete: () -> Unit,
    onDismissDialog: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismissDialog,
            title = { Text("Delete All History") },
            text = { Text("Are you sure you want to delete all history?") },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirmDelete()
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismissDialog()
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}