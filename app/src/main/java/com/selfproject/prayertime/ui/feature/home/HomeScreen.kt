package com.selfproject.prayertime.ui.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.selfproject.prayertime.ui.feature.home.components.HomeDate
import com.selfproject.prayertime.ui.feature.home.components.HomeDhikrCard
import com.selfproject.prayertime.ui.feature.home.components.HomeLocation
import com.selfproject.prayertime.ui.feature.home.components.HomePrayerList
import com.selfproject.prayertime.ui.feature.home.components.HomeTimerPrayer
import com.selfproject.prayertime.ui.theme.PrayerTimeTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    val timerState by viewModel.timerState.collectAsState()

    val dataText = viewModel.todayDate
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0F2027),
                        Color(0xFF203A43),
                        Color(0xFF2C5364)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeDate(
                dateText = dataText
            )
            Spacer(modifier = Modifier.height(16.dp))
            HomeLocation()
            Spacer(modifier = Modifier.height(32.dp))
            HomeTimerPrayer(
                hours = timerState.hours,
                minutes = timerState.minutes,
                seconds = timerState.seconds,
                progress = timerState.progress,
                nextPrayerName = timerState.nextPrayerName,
                nextPrayerTime = timerState.nextPrayerTime
            )
            Spacer(modifier = Modifier.height(32.dp))
            HomePrayerList(
                state = state
            )
            Spacer(modifier = Modifier.height(24.dp))
            HomeDhikrCard()
        }
    }
}


// Preview
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PrayerTimeTheme {
    }
}