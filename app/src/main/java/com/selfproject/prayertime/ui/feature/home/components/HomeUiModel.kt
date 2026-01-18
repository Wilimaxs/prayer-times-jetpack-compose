package com.selfproject.prayertime.ui.feature.home.components

import androidx.compose.ui.graphics.vector.ImageVector

data class PrayerTimeItem(
    val name: String,
    val time: String,
    val icon: ImageVector,
    val isActive: Boolean = false
)

data class TimerState(
    val hours: String = "00",
    val minutes: String = "00",
    val seconds: String = "00",
    val progress: Float = 0f,
    val activePrayer: String = "Fajr",
    val nextPrayerName: String = "Sholat",
    val nextPrayerTime: String = "--:--"
)