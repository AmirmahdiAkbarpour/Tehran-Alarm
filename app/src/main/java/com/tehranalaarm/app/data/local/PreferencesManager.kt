package com.tehranalaarm.app.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tehran_alarm_prefs")

class PreferencesManager(private val context: Context) {

    companion object {
        private val SELECTED_CITY = stringPreferencesKey("selected_city")
        private val DEVICE_ID = stringPreferencesKey("device_id")
        private val FCM_TOKEN = stringPreferencesKey("fcm_token")
        private val NOTIFICATION_ENABLED = stringPreferencesKey("notification_enabled")
    }

    val selectedCity: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[SELECTED_CITY] ?: "Tehran"
    }

    val deviceId: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[DEVICE_ID] ?: ""
    }

    suspend fun saveSelectedCity(city: String) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_CITY] = city
        }
    }

    suspend fun saveDeviceId(id: String) {
        context.dataStore.edit { preferences ->
            preferences[DEVICE_ID] = id
        }
    }

    suspend fun saveFcmToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[FCM_TOKEN] = token
        }
    }
}
