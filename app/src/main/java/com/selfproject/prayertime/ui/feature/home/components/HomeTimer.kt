package com.selfproject.prayertime.ui.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.selfproject.prayertime.ui.theme.GlassBorderColor
import com.selfproject.prayertime.ui.theme.GlassPanelColor
import com.selfproject.prayertime.ui.theme.PrimaryBlue
import com.selfproject.prayertime.ui.theme.TextWhite
import com.selfproject.prayertime.ui.theme.TextWhiteSecondary
import com.selfproject.prayertime.ui.theme.TextWhiteTertiary

// Composable time prayer
@Composable
fun HomeTimerPrayer() {
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