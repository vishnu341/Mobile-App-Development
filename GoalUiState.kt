package com.example.fitmeappfinalllimplementationv1.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("Splash")
    object Home:  Screen("Home")
    object Settings:  Screen("Settings")


}