package com.tehranalaarm.app.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.util.Log

class TehranAlarmFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("FCM", "Message received from: ${remoteMessage.from}")

        // Handle data payload
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("FCM", "Message data payload: ${remoteMessage.data}")
            handleDataMessage(remoteMessage.data)
        }

        // Handle notification payload
        remoteMessage.notification?.let {
            Log.d("FCM", "Message Notification Body: ${it.body}")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "New token generated: $token")
        sendTokenToServer(token)
    }

    private fun handleDataMessage(data: Map<String, String>) {
        // Handle alert data
        val alertType = data["type"] ?""
        val city = data["city"] ?: "تهران"
        val timestamp = data["timestamp"] ?: ""

        Log.d("FCM", "Alert Type: $alertType, City: $city, Timestamp: $timestamp")
    }

    private fun sendTokenToServer(token: String) {
        // TODO: Send token to backend for device registration
        Log.d("FCM", "Token sent to server: $token")
    }
}
