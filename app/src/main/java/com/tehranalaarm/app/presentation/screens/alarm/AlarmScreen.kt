package com.tehranalaarm.app.presentation.screens.alarm

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.RingtoneManager
import android.media.SoundPool
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.WindowManager
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun AlarmScreen(navController: NavController) {
    val context = LocalContext.current
    var countdownSeconds by remember { mutableIntStateOf(10) }
    var showStopButton by remember { mutableStateOf(false) }
    var isRunning by remember { mutableStateOf(true) }

    val vibrator = context.getSystemService(Vibrator::class.java) as Vibrator
    val audioManager = context.getSystemService(AudioManager::class.java) as AudioManager

    // Pulsing background color animation
    val targetColor = if (isRunning) Color(0xFFD32F2F) else Color(0xFF8B0000)
    val animatedColor by animateColorAsState(
        targetValue = targetColor,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "alarm_pulsing"
    )

    // Handle window properties
    DisposableEffect(Unit) {
        val window = (context as? android.app.Activity)?.window
        window?.apply {
            addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
            addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        }
        onDispose {
            window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    // Start alarm sound and vibration
    LaunchedEffect(Unit) {
        // Play siren sound
        val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone = RingtoneManager.getRingtone(context, ringtoneUri)
        ringtone?.play()

        // Vibration pattern
        val pattern = longArrayOf(0, 500, 200, 500, 200, 500)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val effect = VibrationEffect.createWaveform(pattern)
            vibrator.vibrate(effect)
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(pattern, 0)
        }
    }

    // Countdown logic
    LaunchedEffect(countdownSeconds) {
        if (isRunning && countdownSeconds > 0) {
            delay(1000)
            countdownSeconds--
        } else if (countdownSeconds == 0 && isRunning) {
            showStopButton = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedColor)
    ) {
        // Glow effect overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black.copy(alpha = 0.3f)
                )
                .alpha(if (isRunning) 0.4f else 0.2f)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Title
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "تهران آلارم",
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayMedium
                )

                Text(
                    text = "حمله هوایی به شهر تهران",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFFFC107),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 12.dp),
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            // Safety Instructions Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF8B0000).copy(alpha = 0.8f)
                ),
                elevation = CardDefaults.cardElevation(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "دستورالعمل های ایمنی",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )

                    val instructions = listOf(
                        "• آرام بمانید",
                        "• از پنجره ها دور شوید",
                        "• به نزدیکترین جای ایمن برفت",
                        "• از دستورالعمل های رسمی پیروی کنید"
                    )

                    instructions.forEach { instruction ->
                        Text(
                            text = instruction,
                            fontSize = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Right,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            // Stop Button (appears after 10 seconds)
            if (showStopButton) {
                Button(
                    onClick = {
                        isRunning = false
                        vibrator.cancel()
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .size(height = 56.dp, width = 200.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFC107)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "قطع آلارم",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            // Bottom spacing
            Box(modifier = Modifier.size(16.dp))
        }
    }
}
