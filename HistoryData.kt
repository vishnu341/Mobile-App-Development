package com.example.fitmeappfinalllimplementationv1.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.fitmeappfinalllimplementationv1.presentation.settings.SettingsScreen
import com.example.fitmeappfinalllimplementationv1.screens.AnimatedSplashScreen



@Composable
fun  SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = Screen.Splash.route )
    {
        composable(route= Screen.Splash.route) {
            AnimatedSplashScreen(navController)
        }
        composable(route= Screen.Home.route) {
            MainScreen()
        }
        composable(route= Screen.Settings.route) {
            SettingsScreen()
        }
    }
}