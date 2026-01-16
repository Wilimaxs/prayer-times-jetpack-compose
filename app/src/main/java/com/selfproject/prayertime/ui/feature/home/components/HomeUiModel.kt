package com.selfproject.prayertime.ui.feature.home.components

import androidx.compose.ui.graphics.vector.ImageVector

data class PrayerTimeItem(
    val name: String,
    val time: String,
    val icon: ImageVector,
    val isActive: Boolean = false
)