package com.tehranalaarm.app.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tehranalaarm.app.domain.models.AlertStatus
import com.tehranalaarm.app.domain.repository.AlertRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlertViewModel @Inject constructor(
    private val alertRepository: AlertRepository
) : ViewModel() {

    private val _alertStatus = MutableStateFlow<AlertStatus?>(null)
    val alertStatus: StateFlow<AlertStatus?> = _alertStatus.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        fetchAlertStatus()
    }

    fun fetchAlertStatus() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                val status = alertRepository.getAlertStatus()
                _alertStatus.value = status
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
