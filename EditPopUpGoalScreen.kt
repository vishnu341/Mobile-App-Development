package com.example.fitmeappfinalllimplementationv1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitmeappfinalllimplementationv1.presentation.settings.SettingsScreen
import com.example.fitmeappfinalllimplementationv1.screens.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.navDeepLink


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.GoalManagement.route) {
            //GoalManagementScreen()
            GoalSettingsScreen()
        }
        composable(route = BottomBarScreen.History.route) {
            HistoryPage()
        }

        composable(route = BottomBarScreen.Settings.route) {
            SettingsScreen()
        }
    }

}