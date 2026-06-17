package com.tehranalaarm.app.presentation.screens.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tehranalaarm.app.presentation.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var alphaValue by remember { mutableFloatStateOf(0f) }

    val animatedAlpha by animateFloatAsState(
        targetValue = alphaValue,
        animationSpec = tween(durationMillis = 1500),
        label = "splash_fade_in"
    )

    LaunchedEffect(Unit) {
        alphaValue = 1f
        delay(3000)
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Splash.route) { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0A0A0A)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            // App Logo/Icon
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .alpha(animatedAlpha)
                    .background(
                        color = Color(0xFFD32F2F),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(30.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "⚠️",
                    fontSize = 60.sp,
                )
            }

            // App Name
            Text(
                text = "تهران آلارم",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.alpha(animatedAlpha),
                style = MaterialTheme.typography.displayLarge
            )

            // Subtitle
            Text(
                text = "سیستم هشدار حملات هوایی",
                fontSize = 16.sp,
                color = Color(0xFFB3B3B3),
                modifier = Modifier.alpha(animatedAlpha),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
