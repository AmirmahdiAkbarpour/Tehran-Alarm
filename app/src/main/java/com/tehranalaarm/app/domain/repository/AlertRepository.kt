package com.tehranalaarm.app.domain.repository

import com.tehranalaarm.app.domain.models.AlertStatus
import com.tehranalaarm.app.domain.models.Alert
import kotlinx.coroutines.flow.Flow

interface AlertRepository {
    suspend fun getAlertStatus(): AlertStatus
    suspend fun getCityAlerts(cityId: String): List<Alert>
    fun observeAlertStatus(): Flow<AlertStatus>
}
