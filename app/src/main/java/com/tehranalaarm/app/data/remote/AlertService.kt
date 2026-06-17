package com.tehranalaarm.app.data.remote

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Body

interface AlertService {

    @GET("api/v1/alerts/status")
    suspend fun getAlertStatus(): AlertStatusResponse

    @GET("api/v1/alerts/city/{cityId}")
    suspend fun getCityAlerts(@Path("cityId") cityId: String): List<AlertResponse>

    @POST("api/v1/devices/register")
    suspend fun registerDevice(@Body request: DeviceRegistrationRequest): DeviceRegistrationResponse
}

data class AlertStatusResponse(
    val status: String,
    val timestamp: Long,
    val city: String
)

data class AlertResponse(
    val id: String,
    val type: String,
    val city: String,
    val timestamp: Long,
    val description: String
)

data class DeviceRegistrationRequest(
    val fcmToken: String,
    val deviceId: String,
    val city: String,
    val appVersion: String
)

data class DeviceRegistrationResponse(
    val success: Boolean,
    val message: String,
    val deviceId: String
)
