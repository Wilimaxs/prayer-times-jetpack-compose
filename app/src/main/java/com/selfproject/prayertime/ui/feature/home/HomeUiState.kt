package com.selfproject.prayertime.ui.feature.home

import com.selfproject.prayertime.data.model.PrayerData

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val prayerData: PrayerData) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}