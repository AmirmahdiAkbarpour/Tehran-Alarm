package com.tehranalaarm.app.domain.models

data class AlertStatus(
    val status: String,
    val timestamp: Long,
    val city: String,
    val description: String = ""
)

data class Alert(
    val id: String,
    val type: String,
    val city: String,
    val timestamp: Long,
    val description: String,
    val severity: Int = 1
)

data class Device(
    val id: String,
    val fcmToken: String,
    val city: String,
    val appVersion: String,
    val registeredAt: Long
)
