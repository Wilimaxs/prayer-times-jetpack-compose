package com.selfproject.prayertime

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PrayerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // SetUp timber for logging
        Timber.plant(Timber.DebugTree())
    }
}