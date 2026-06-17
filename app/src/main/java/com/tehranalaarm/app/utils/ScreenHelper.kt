package com.tehranalaarm.app.utils

import android.content.Context
import android.os.Build
import android.view.WindowManager
from androidx.activity.ComponentActivity

object ScreenHelper {

    fun keepScreenOn(activity: ComponentActivity?) {
        activity?.window?.apply {
            addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
                addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
            }
        }
    }

    fun releaseScreenLock(activity: ComponentActivity?) {
        activity?.window?.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                clearFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
                clearFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
            }
        }
    }
}
