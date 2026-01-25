package com.selfproject.prayertime.ui.feature.home

import androidx.compose.ui.graphics.vector.ImageVector
import com.selfproject.prayertime.data.model.PrayerData

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val prayerData: PrayerData) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}

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
    val nextPrayerName: String = "",
    val nextPrayerTime: String = "--:--"
)

data class LocationNameDefault(
    val locationName: String = "",
)

// wrapper
data class HomeScreenState(
    val homeUiState: HomeUiState = HomeUiState.Loading,
    val prayerTimes: List<PrayerTimeItem> = emptyList(),
    val timerState: TimerState = TimerState(),
    val locationName: LocationNameDefault = LocationNameDefault(),
    val fullTodayDate: String = "",
    val isRefreshing: Boolean = false,
    val latitude: String? = null,
    val longitude: String? = null,
)