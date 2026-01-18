package com.selfproject.prayertime.ui.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.selfproject.prayertime.ui.feature.home.HomeUiState
import com.selfproject.prayertime.ui.theme.GlassBorderActiveColor
import com.selfproject.prayertime.ui.theme.GlassPanelActiveColor
import com.selfproject.prayertime.ui.theme.GlassPanelColor
import com.selfproject.prayertime.ui.theme.PrimaryBlue
import com.selfproject.prayertime.ui.theme.TextWhite
import com.selfproject.prayertime.ui.theme.TextWhiteSecondary
import com.selfproject.prayertime.ui.utils.reusable.shimmerEffect

//horizontal prayer time list
@Composable
fun HomePrayerList(
    state: HomeUiState,
    activePrayerName: String
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        when (state) {
            is HomeUiState.Loading -> {
                items(5) {
                    PrayerItemShimmer()
                }
            }

            is HomeUiState.Success -> {
                val data = state.prayerData.timings

                val prayerTimes = listOf(
                    PrayerTimeItem(
                        "Fajr",
                        data.fajr,
                        Icons.Default.WbTwilight,
                        activePrayerName == "Fajr"
                    ),
                    PrayerTimeItem(
                        "Dhuhr",
                        data.dhuhr,
                        Icons.Default.LightMode,
                        activePrayerName == "Dhuhr"
                    ),
                    PrayerTimeItem(
                        "Asr",
                        data.asr,
                        Icons.Default.WbSunny,
                        activePrayerName == "Asr"
                    ),
                    PrayerTimeItem(
                        "Maghrib",
                        data.maghrib,
                        Icons.Default.NightsStay,
                        activePrayerName == "Maghrib"
                    ),
                    PrayerTimeItem(
                        "Isha",
                        data.isha,
                        Icons.Default.Bedtime,
                        activePrayerName == "Isha"
                    )
                )

                items(prayerTimes) { item ->
                    PrayerItemCard(item)
                }
            }

            is HomeUiState.Error -> {
                item {
                    Text(
                        text = state.message,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

//Prayer Item Card
@Composable
private fun PrayerItemCard(item: PrayerTimeItem) {
    val opacity = if (item.isActive) 1f else 0.5f
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (item.isActive) GlassPanelActiveColor else GlassPanelColor)
            .border(
                width = 1.dp,
                color = if (item.isActive) GlassBorderActiveColor else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .then(
                if (item.isActive) Modifier.shadow(
                    elevation = 15.dp,
                    spotColor = PrimaryBlue.copy(alpha = 0.16f)
                ) else Modifier
            )
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = item.name,
            color = if (item.isActive) PrimaryBlue else TextWhiteSecondary
                .copy(alpha = opacity),
            fontSize = 12.sp,
            fontWeight = if (item.isActive) FontWeight.Bold else FontWeight.Normal
        )
        Spacer(modifier = Modifier.height(4.dp))
        Icon(
            imageVector = item.icon,
            contentDescription = null,
            tint = if (item.isActive) PrimaryBlue else TextWhite.copy(alpha = 0.4f),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = item.time,
            color = if (item.isActive) TextWhite else TextWhite.copy(alpha = 0.9f)
                .copy(alpha = opacity),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

//composable prayer item shimmer
@Composable
fun PrayerItemShimmer() {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.1f))
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(12.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(14.dp)
                .clip(RoundedCornerShape(4.dp))
                .shimmerEffect()
        )
    }
}