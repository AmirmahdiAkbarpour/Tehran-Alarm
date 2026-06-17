package com.tehranalaarm.app.data

import android.util.Log

object Constants {
    // API Configuration
    const val BASE_API_URL = "https://api.tehran-alarm.ir/"
    const val API_TIMEOUT_SECONDS = 30L

    // WebSocket Configuration
    const val WEBSOCKET_URL = "wss://ws.tehran-alarm.ir/alerts"
    const val WEBSOCKET_RECONNECT_INTERVAL_MS = 5000L

    // Firebase Configuration
    const val FCM_TOPIC_ALERTS = "tehran_alerts"
    const val FCM_TOPIC_NOTIFICATIONS = "tehran_notifications"

    // UI Configuration
    const val ALARM_DURATION_SECONDS = 10
    const val VIBRATION_PATTERN_MS = longArrayOf(0, 500, 200, 500, 200, 500)
    const val SPLASH_SCREEN_DURATION_MS = 3000L

    // Database Configuration
    const val DATABASE_NAME = "tehran_alarm_db"

    // Preferences
    const val PREFS_NAME = "tehran_alarm_prefs"
    const val PREF_DEVICE_ID = "device_id"
    const val PREF_FCM_TOKEN = "fcm_token"
    const val PREF_SELECTED_CITY = "selected_city"
    const val PREF_NOTIFICATIONS_ENABLED = "notifications_enabled"

    // Logging
    const val LOG_TAG = "TehranAlarm"

    fun log(message: String) {
        Log.d(LOG_TAG, message)
    }

    fun logError(message: String, throwable: Throwable? = null) {
        Log.e(LOG_TAG, message, throwable)
    }
}
