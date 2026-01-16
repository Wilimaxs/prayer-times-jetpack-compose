package com.selfproject.prayertime.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfproject.prayertime.data.common.Resource
import com.selfproject.prayertime.data.respository.PrayerRepository
import com.selfproject.prayertime.ui.feature.home.components.TimerState
import com.selfproject.prayertime.ui.utils.helpers.DateHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PrayerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    val uiState = _uiState.asStateFlow()

    // Get current date for indonesia
    val todayDate: String = DateHelper.getCurrentDate()

    private val _timerState = MutableStateFlow(TimerState())
    val timerState = _timerState.asStateFlow()

    init {
        getPrayerTimes("Jakarta", "Indonesia")
        startCountdown()
    }

    private fun startCountdown() {
        viewModelScope.launch {
            val durationInMillis = (1 * 3600 * 1000L) + (23 * 60 * 1000L) + (45 * 1000L)
            val targetTime = System.currentTimeMillis() + durationInMillis

            while (isActive) {
                val currentTime = System.currentTimeMillis()
                val remaining = targetTime - currentTime

                if (remaining <= 0) {
                    _timerState.value = TimerState(progress = 0f)
                    break
                }
                val progressValue = (remaining.toFloat() / durationInMillis.toFloat()).coerceIn(0f, 1f)
                val hours = (remaining / (1000 * 60 * 60)) % 24
                val minutes = (remaining / (1000 * 60)) % 60
                val seconds = (remaining / 1000) % 60

                _timerState.value = TimerState(
                    hours = "%02d".format(hours),
                    minutes = "%02d".format(minutes),
                    seconds = "%02d".format(seconds),
                    progress = progressValue
                )
                delay(1000L)
            }
        }
    }



    fun getPrayerTimes(city: String, country: String) {
        viewModelScope.launch {
            repository.getTimingsByCity(city, country)
                .collect { result ->
                    _uiState.value = when (result) {
                        is Resource.Loading -> HomeUiState.Loading
                        is Resource.Success -> {
                            if (result.data != null) {
                                HomeUiState.Success(result.data)
                            } else {
                                HomeUiState.Error("No data found")
                            }
                        }

                        is Resource.Error -> {
                            HomeUiState.Error(result.message ?: "Something went wrong")
                        }
                    }
                }
        }
    }
}