package com.tehranalaarm.app.services.websocket

import android.util.Log
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class AlertWebSocketListener(
    private val onAlertReceived: (alertData: String) -> Unit,
    private val onConnectionOpen: () -> Unit,
    private val onConnectionClosed: () -> Unit,
    private val onError: (error: String) -> Unit
) : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
        super.onOpen(webSocket, response)
        Log.d("WebSocket", "Connection opened")
        onConnectionOpen()
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)
        Log.d("WebSocket", "Message received: $text")
        onAlertReceived(text)
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)
        Log.d("WebSocket", "Binary message received")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)
        Log.d("WebSocket", "Connection closing: $reason")
        webSocket.close(code, reason)
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        Log.d("WebSocket", "Connection closed")
        onConnectionClosed()
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
        super.onFailure(webSocket, t, response)
        Log.e("WebSocket", "Error: ${t.message}")
        onError(t.message ?: "Unknown WebSocket error")
    }
}
