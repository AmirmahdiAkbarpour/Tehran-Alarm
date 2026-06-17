package com.tehranalaarm.app.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class RatingManager @Inject constructor(
    private val preferences: SharedPreferences
) {

    companion object {
        private const val KEY_LAST_RATING = "last_rating"
        private const val KEY_RATING_COUNT = "rating_count"
        private const val KEY_LAST_RATING_TIMESTAMP = "last_rating_timestamp"
    }

    fun saveRating(rating: Int, comment: String = "") {
        preferences.edit().apply {
            putInt(KEY_LAST_RATING, rating)
            putLong(KEY_LAST_RATING_TIMESTAMP, System.currentTimeMillis())
            apply()
        }

        val currentCount = getRatingCount()
        preferences.edit().putInt(KEY_RATING_COUNT, currentCount + 1).apply()
    }

    fun getLastRating(): Int {
        return preferences.getInt(KEY_LAST_RATING, 0)
    }

    fun getRatingCount(): Int {
        return preferences.getInt(KEY_RATING_COUNT, 0)
    }

    fun getLastRatingTimestamp(): Long {
        return preferences.getLong(KEY_LAST_RATING_TIMESTAMP, 0L)
    }
}
