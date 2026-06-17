package com.tehranalaarm.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TehranAlarmApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
