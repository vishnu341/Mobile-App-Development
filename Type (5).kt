package com.example.fitmeappfinalllimplementationv1.presentation.settings

import UserPreferences
import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfinalappimplementation.ui.theme.AnimatedSplashScreenColor

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val settingsViewModel = remember { SettingVM(context.applicationContext as Application, context) }
    val isHistoryPageEnabled by settingsViewModel.isHistoryPageEnabled.collectAsState()
    val isGoalPageEnabled by settingsViewModel.isGoalPageEnabled.collectAsState()

    Column {
        Text("Settings")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Enable History Page")

            Switch(
                checked = isHistoryPageEnabled,
                onCheckedChange = { enabled ->
                    settingsViewModel.setHistoryPageEnabled(enabled)
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Enable Goal Page")

            Switch(
                checked = isGoalPageEnabled,
                onCheckedChange = { enabled ->
                    settingsViewModel.setGoalPageEnabled(enabled)
                }
            )
        }
    }
}
