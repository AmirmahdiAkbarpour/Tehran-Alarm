package com.tehranalaarm.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tehranalaarm.app.presentation.navigation.Screen
import com.tehranalaarm.app.presentation.screens.alarm.AlarmScreen
import com.tehranalaarm.app.presentation.screens.home.HomeScreen
import com.tehranalaarm.app.presentation.screens.splash.SplashScreen
import com.tehranalaarm.app.ui.theme.TehranAlarmTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TehranAlarmTheme {
                TehranAlarmApp()
            }
        }
    }
}

@Composable
fun TehranAlarmApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = Modifier.background(Color(0xFF121212))
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Alarm.route) {
            AlarmScreen(navController = navController)
        }
    }
}
