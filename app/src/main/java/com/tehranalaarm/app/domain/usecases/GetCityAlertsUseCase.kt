package com.tehranalaarm.app.domain.usecases

import com.tehranalaarm.app.domain.models.Alert
import com.tehranalaarm.app.domain.repository.AlertRepository
import javax.inject.Inject

class GetCityAlertsUseCase @Inject constructor(
    private val alertRepository: AlertRepository
) {

    suspend operator fun invoke(cityId: String): List<Alert> {
        return alertRepository.getCityAlerts(cityId)
    }
}
