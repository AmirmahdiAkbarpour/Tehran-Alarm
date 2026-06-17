package com.tehranalaarm.app.presentation.screens.rating

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tehranalaarm.app.data.local.RatingManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RatingViewModel @Inject constructor(
    private val ratingManager: RatingManager
) : ViewModel() {

    fun submitRating(rating: Int, comment: String = "") {
        viewModelScope.launch {
            ratingManager.saveRating(rating, comment)
        }
    }

    fun getRatingCount(): Int {
        return ratingManager.getRatingCount()
    }
}
