package com.example.fitmeappfinalllimplementationv1.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.example.myfinalappimplementation.ui.theme.AnimatedSplashScreenColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen()
{
    val navController=rememberNavController()
    Scaffold(
        topBar = { TopAppBarWithIcon(navController = navController) },
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens= listOf(
        BottomBarScreen.Home,
        BottomBarScreen.GoalManagement,
        BottomBarScreen.History
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination=navBackStackEntry?.destination
    
    
    BottomNavigation(
        backgroundColor = AnimatedSplashScreenColor,
        contentColor = Color.White
    ) {
        screens.forEach { screen ->
            AddItem(screen=screen,
                currentDestination=currentDestination,
                navController=navController)
        }


        }
        
    }


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = { Text(text = screen.title) },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon"

            )
        },
        selected = currentDestination?.hierarchy?.any
        {
            it.route == screen.route
        } == true,
        onClick ={
            navController.navigate(screen.route)
        }
        )

}

@Composable
fun TopAppBarWithIcon(navController: NavHostController) {

    val screens=BottomBarScreen.Settings
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination=navBackStackEntry?.destination
    TopAppBar(
        title = { Text("FitMeApp") },
        actions = {
            IconButton(onClick = { navController.navigate(screens.route) }) {
                Icon(Icons.Filled.Settings, contentDescription = "Settings")
            }
        },
        backgroundColor = AnimatedSplashScreenColor,
        contentColor = Color.White,
        elevation = 12.dp
    )
}