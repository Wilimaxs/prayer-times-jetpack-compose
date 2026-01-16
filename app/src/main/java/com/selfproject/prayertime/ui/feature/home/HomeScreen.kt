package com.selfproject.prayertime.ui.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NightsStay
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.WbTwilight
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.selfproject.prayertime.ui.theme.GlassBorderActiveColor
import com.selfproject.prayertime.ui.theme.GlassBorderColor
import com.selfproject.prayertime.ui.theme.GlassPanelActiveColor
import com.selfproject.prayertime.ui.theme.GlassPanelColor
import com.selfproject.prayertime.ui.theme.PrayerTimeTheme
import com.selfproject.prayertime.ui.theme.PrimaryBlue
import com.selfproject.prayertime.ui.theme.TextWhite
import com.selfproject.prayertime.ui.theme.TextWhiteSecondary
import com.selfproject.prayertime.ui.theme.TextWhiteTertiary

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

@Composable
fun HomeScreen() {
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
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Date()
            Spacer(modifier = Modifier.height(16.dp))
            Location()
            Spacer(modifier = Modifier.height(32.dp))
            TimePrayer()
            Spacer(modifier = Modifier.height(32.dp))
            PrayerTimeList()
            Spacer(modifier = Modifier.height(24.dp))
            DailyDhikrCard()
        }
    }
}

// Composable Date
@Composable
private fun Date() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            Icons.Default.CalendarMonth,
            contentDescription = "Current Date",
            tint = TextWhite.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            "Wednesday, 20 April 2023",
            color = TextWhite.copy(alpha = 0.9f),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}

// Composable Location
@Composable
private fun Location() {
    Row(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = GlassBorderColor,
                shape = RoundedCornerShape(50.dp)
            )
            .background(
                color = Color.White.copy(alpha = 0.05f),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            Icons.Default.LocationOn,
            contentDescription = "Location",
            tint = PrimaryBlue,
            modifier = Modifier.height(18.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "Jakarta, Indonesia",
            color = TextWhite,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            letterSpacing = 0.5.sp
        )
        Spacer(modifier = Modifier.width(6.dp))
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = "Expand More",
            tint = TextWhiteSecondary
        )
    }
}

// Composable time prayer
@Composable
private fun TimePrayer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = GlassBorderColor,
                shape = RoundedCornerShape(24.dp)
            )
            .clip(RoundedCornerShape(24.dp))
            .background(
                color = GlassPanelColor
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "NEXT PRAYER IN",
                color = TextWhite.copy(alpha = 0.7f),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.5.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Time()
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Asr",
                    color = TextWhite,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "15:45",
                    color = TextWhiteSecondary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
        LinearProgressIndicator(
            progress = { 0.65f },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .align(Alignment.BottomCenter),
            color = PrimaryBlue,
            trackColor = PrimaryBlue.copy(alpha = 0.3f)
        )
    }
}

// Composable Time
@Composable
private fun Time() {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "01",
                color = TextWhite,
                fontSize = 48.sp, // text-5xl
                fontWeight = FontWeight.Bold,
                letterSpacing = (-2).sp
            )
            Text(
                text = "HOURS",
                color = TextWhiteTertiary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = ":",
            color = TextWhiteTertiary,
            fontSize = 30.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "23",
                color = TextWhite,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-2).sp
            )
            Text(
                text = "MINS",
                color = TextWhiteTertiary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = ":",
            color = TextWhiteTertiary,
            fontSize = 30.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(top = 4.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "45",
                color = PrimaryBlue,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-2).sp
            )
            Text(
                text = "SECS",
                color = TextWhiteTertiary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 1.sp
            )
        }
    }
}

// composable Daily Dhikr Card
@Composable
fun DailyDhikrCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = GlassPanelColor)
            .border(
                width = 1.dp,
                color = GlassBorderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(color = Color(0xFF10B981).copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = Color(0xFF34D399),
                modifier = Modifier.size(28.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = "Daily Dhikr",
                color = TextWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Morning Adhkar",
                color = TextWhiteSecondary, // Text-white/50
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Go",
            tint = TextWhite.copy(alpha = 0.3f),
            modifier = Modifier.size(28.dp)
        )
    }
}

//horizontal prayer time list
@Composable
private fun PrayerTimeList() {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 4.dp)
    ) {
        items(dummyPrayerTimes) { item ->
            PrayerItemCard(item)
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

// Preview
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PrayerTimeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            HomeScreen()
        }
    }
}