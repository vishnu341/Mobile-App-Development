 package com.example.fitmeappfinalllimplementationv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.fitmeappfinalllimplementationv1.navigation.SetupNavGraph
import com.example.myfinalappimplementation.ui.theme.MyFinalAppImplementationTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFinalAppImplementationTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController=navController)
            }
            FitMeApplication()
        }

            }
        }



