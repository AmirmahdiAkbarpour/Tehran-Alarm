package com.tehranalaarm.app.data.repository

import com.tehranalaarm.app.data.remote.AlertService
import com.tehranalaarm.app.domain.models.Alert
import com.tehranalaarm.app.domain.models.AlertStatus
import com.tehranalaarm.app.domain.repository.AlertRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class AlertRepositoryImpl @Inject constructor(
    private val alertService: AlertService
) : AlertRepository {

    override suspend fun getAlertStatus(): AlertStatus {
        val response = alertService.getAlertStatus()
        return AlertStatus(
            status = response.status,
            timestamp = response.timestamp,
            city = response.city
        )
    }

    override suspend fun getCityAlerts(cityId: String): List<Alert> {
        return alertService.getCityAlerts(cityId).map { response ->
            Alert(
                id = response.id,
                type = response.type,
                city = response.city,
                timestamp = response.timestamp,
                description = response.description
            )
        }
    }

    override fun observeAlertStatus(): Flow<AlertStatus> {
        // TODO: Implement WebSocket streaming for real-time alerts
        return flowOf()
    }
}
