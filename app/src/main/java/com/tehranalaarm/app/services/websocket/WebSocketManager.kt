package com.tehranalaarm.app.services.websocket

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebSocketManager @Inject constructor(
    private val okHttpClient: OkHttpClient
) {

    private var webSocket: WebSocket? = null
    private val baseUrl = "wss://ws.tehran-alarm.ir/alerts"

    fun connect(
        onAlertReceived: (alertData: String) -> Unit,
        onConnectionOpen: () -> Unit,
        onConnectionClosed: () -> Unit,
        onError: (error: String) -> Unit
    ) {
        try {
            val request = Request.Builder()
                .url(baseUrl)
                .build()

            val listener = AlertWebSocketListener(
                onAlertReceived = onAlertReceived,
                onConnectionOpen = onConnectionOpen,
                onConnectionClosed = onConnectionClosed,
                onError = onError
            )

            webSocket = okHttpClient.newWebSocket(request, listener)
            Log.d("WebSocketManager", "WebSocket connection initiated")
        } catch (e: Exception) {
            Log.e("WebSocketManager", "Failed to connect: ${e.message}")
            onError(e.message ?: "Failed to connect")
        }
    }

    fun disconnect() {
        try {
            webSocket?.close(1000, "Client disconnecting")
            webSocket = null
            Log.d("WebSocketManager", "WebSocket disconnected")
        } catch (e: Exception) {
            Log.e("WebSocketManager", "Error disconnecting: ${e.message}")
        }
    }

    fun send(message: String): Boolean {
        return try {
            webSocket?.send(message) ?: false
        } catch (e: Exception) {
            Log.e("WebSocketManager", "Error sending message: ${e.message}")
            false
        }
    }

    fun isConnected(): Boolean {
        return webSocket != null
    }
}
