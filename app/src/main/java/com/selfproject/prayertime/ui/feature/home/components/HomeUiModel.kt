package com.selfproject.prayertime.ui.feature.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.ui.graphics.vector.ImageVector

data class PrayerTimeItem(
    val name: String,
    val time: String,
    val icon: ImageVector,
    val isActive: Boolean = false
)

val dummyPrayerTimes = listOf(
    PrayerTimeItem("Fajr", "04:12", Icons.Default.WbTwilight, false),
    PrayerTimeItem("Dhuhr", "12:30", Icons.Default.LightMode, false),
    PrayerTimeItem("Asr", "15:45", Icons.Default.WbSunny, true),
    PrayerTimeItem("Maghrib", "18:20", Icons.Default.NightsStay, false),
    PrayerTimeItem("Isha", "20:05", Icons.Default.Bedtime, false)
)