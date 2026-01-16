package com.selfproject.prayertime.ui.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.selfproject.prayertime.ui.theme.GlassBorderActiveColor
import com.selfproject.prayertime.ui.theme.GlassPanelActiveColor
import com.selfproject.prayertime.ui.theme.GlassPanelColor
import com.selfproject.prayertime.ui.theme.PrimaryBlue
import com.selfproject.prayertime.ui.theme.TextWhite
import com.selfproject.prayertime.ui.theme.TextWhiteSecondary

//horizontal prayer time list
@Composable
fun HomePrayerList() {
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