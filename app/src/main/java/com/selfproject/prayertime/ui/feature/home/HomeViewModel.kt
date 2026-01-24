package com.selfproject.prayertime.ui.feature.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfproject.prayertime.data.common.Resource
import com.selfproject.prayertime.data.model.PrayerData
import com.selfproject.prayertime.data.respository.PrayerRepository
import com.selfproject.prayertime.ui.feature.home.components.LocationNameDefault
import com.selfproject.prayertime.ui.feature.home.components.TimerState
import com.selfproject.prayertime.ui.utils.helpers.DateHelper
import com.selfproject.prayertime.ui.utils.helpers.getLocationName
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.Duration.between
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PrayerRepository,
    @param:ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(value = HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _timerState = MutableStateFlow(TimerState())
    val timerState = _timerState.asStateFlow()

    private val _locationName = MutableStateFlow(LocationNameDefault())
    val locationName = _locationName.asStateFlow()


    // Get current date with Day for indonesia
    val todayDate: String = DateHelper.getCurrentDate()

    // Get just current date
    val todayDateOnly: String = DateHelper.getCurrentDate(withDayName = false)


    private var countdownJob: Job? = null


    private fun startCountdown(data: PrayerData) {
        // cancel previous job
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            // Formatter for time string (HH:mm)
            val formatter = DateTimeFormatter.ofPattern("HH:mm")

            // Map of prayer times become key-value pairs (Fajr -> LocalTime)
            val prayerTimes = mapOf(
                "Fajr" to LocalTime.parse(data.timings.fajr.take(5), formatter),
                "Dhuhr" to LocalTime.parse(data.timings.dhuhr.take(5), formatter),
                "Asr" to LocalTime.parse(data.timings.asr.take(5), formatter),
                "Maghrib" to LocalTime.parse(data.timings.maghrib.take(5), formatter),
                "Isha" to LocalTime.parse(data.timings.isha.take(5), formatter)
            )

            // Loop for every 1 second until the next prayer time
            // Coroutine will be cancelled when the viewmodel is destroyed
            while (isActive) {
                // Get current time
                val now = LocalDateTime.now()
                val currentTime = now.toLocalTime()

                // Change prayerTimes to list for indexing
                val prayerTimesEntries = prayerTimes.entries.toList()
                // looping form index 0 until current prayer time
                val nextPrayerIndex =
                    prayerTimesEntries.indexOfFirst { it.value.isAfter(currentTime) }

                // prayer time pair before current time
                val previousPrayerTime: Pair<String, LocalDateTime>
                // prayer time after current time
                val nextPrayerTime: Map.Entry<String, LocalTime>
                // prater time for check next day or current day
                val targetDateTime: LocalDateTime

                // if next prayer time is found
                if (nextPrayerIndex != -1) {
                    // next prayer time data from list
                    nextPrayerTime = prayerTimesEntries[nextPrayerIndex]
                    // combine prayer time with current date
                    targetDateTime = nextPrayerTime.value.atDate(now.toLocalDate())
                    // previous prayer time with current date use if else for fajr condition next days
                    previousPrayerTime = if (nextPrayerIndex > 0) {
                        prayerTimesEntries[nextPrayerIndex - 1].key to prayerTimesEntries[nextPrayerIndex - 1].value.atDate(
                            now.toLocalDate()
                        )
                    } else {
                        prayerTimesEntries.last().key to prayerTimesEntries.last().value.atDate(
                            now.toLocalDate().minusDays(1)
                        )
                    }
                } else {
                    nextPrayerTime = prayerTimesEntries.first()
                    targetDateTime =
                        nextPrayerTime.value.atDate(now.toLocalDate().plusDays(1))
                    previousPrayerTime =
                        prayerTimesEntries.last().key to prayerTimesEntries.last().value.atDate(now.toLocalDate())
                }

                // Get remaining time in milliseconds
                val remainingMillis = between(now, targetDateTime).toMillis()

                // if remaining time < 0 then wait for 1 second and continue loop
                if (remainingMillis <= 0) {
                    delay(500)
                    continue
                }

                // for progress bar logic calculation (0 - 1) (0 = start, 1 = end) (0.5 = middle) etc
                val totalDuration = between(previousPrayerTime.second, targetDateTime).toMillis()
                val progressValue =
                    (remainingMillis.toFloat() / totalDuration.toFloat()).coerceIn(0f, 1f)

                // conversion to hours, minutes, seconds with modulo operator to get only last 2 digits (00 - 59)
                val hours = (remainingMillis / 3600000) % 24
                val minutes = (remainingMillis / 60000) % 60
                val seconds = (remainingMillis / 1000) % 60

                // Update state with remaining time
                _timerState.value = TimerState(
                    hours = "%02d".format(hours),
                    minutes = "%02d".format(minutes),
                    seconds = "%02d".format(seconds),
                    progress = progressValue,
                    activePrayer = previousPrayerTime.first,
                    nextPrayerName = nextPrayerTime.key,
                    nextPrayerTime = nextPrayerTime.value.format(formatter)
                )
                // wait for 1 second
                delay(1000L)
            }
        }
    }


    fun getPrayerTimes(latitude: String = "-6.2088", longitude: String = "106.8456") {
        viewModelScope.launch {
            repository.getTimingsByCity(
                latitude = latitude,
                longitude = longitude,
                date = todayDateOnly
            )
                .collect { result ->
                    Timber.i("Date: Cek date now $todayDateOnly")
                    _uiState.value = when (result) {
                        is Resource.Loading -> HomeUiState.Loading
                        is Resource.Success -> {
                            if (result.data != null) {
                                val lat = latitude.toDoubleOrNull() ?: -6.2088
                                val long = longitude.toDoubleOrNull() ?: 106.8456
                                _locationName.value = LocationNameDefault(
                                    locationName = getLocationName(
                                        context = context,
                                        lat = lat,
                                        long = long
                                    )
                                )
                                startCountdown(result.data)
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