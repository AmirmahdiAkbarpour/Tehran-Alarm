package com.tehranalaarm.app.domain.usecases

import com.tehranalaarm.app.domain.models.AlertStatus
import com.tehranalaarm.app.domain.repository.AlertRepository
import javax.inject.Inject

class GetAlertStatusUseCase @Inject constructor(
    private val alertRepository: AlertRepository
) {

    suspend operator fun invoke(): AlertStatus {
        return alertRepository.getAlertStatus()
    }
}
