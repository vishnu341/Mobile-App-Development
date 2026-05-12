package com.example.fitmeappfinalllimplementationv1.navigation

import com.example.fitmeappfinalllimplementationv1.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
   // val icon_focused: Int
)
{
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.baseline_home_24,
        //icon_focused =R.drawable.baseline_home_24_focused
    )


    object GoalManagement : BottomBarScreen(
        route = "goalManagement",
        title = "GoalSettings",
        icon= R.drawable.outline_track_changes_24,
        //icon_focused =R.drawable.baseline_home_24_focused

    )



    object History : BottomBarScreen(
        route = "history",
        title = "History",
        icon = R.drawable.baseline_history_24

    )
    object Settings : BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = R.drawable.baseline_settings_24,
    )
}


