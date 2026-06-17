package com.tehranalaarm.app.utils

import android.content.Context
import java.util.UUID

object DeviceIdentifier {

    private const val PREF_UNIQUE_ID = "PREF_UNIQUE_ID"

    fun getDeviceId(context: Context): String {
        val preferences = context.getSharedPreferences("device_id", Context.MODE_PRIVATE)
        var deviceId = preferences.getString(PREF_UNIQUE_ID, null)

        if (deviceId == null) {
            deviceId = UUID.randomUUID().toString()
            preferences.edit().putString(PREF_UNIQUE_ID, deviceId).apply()
        }

        return deviceId
    }
}
