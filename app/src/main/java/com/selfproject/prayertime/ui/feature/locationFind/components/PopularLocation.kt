package com.selfproject.prayertime.ui.feature.locationFind.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.selfproject.prayertime.ui.feature.locationFind.LocationItem
import com.selfproject.prayertime.ui.theme.TextWhite
import com.selfproject.prayertime.ui.utils.reusable.ButtonCard

@Composable
fun PopularLocation(
    popularLocations: List<LocationItem>
) {
    Column {
        Text(
            text = "Popular Location",
            color = TextWhite.copy(alpha = 0.7f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(height = 16.dp))
        popularLocations.forEachIndexed { index, locationItem ->
            ButtonCard(
                title = locationItem.city,
                country = locationItem.province,
                onButtonClicked = {}
            )
            if (index < popularLocations.lastIndex) {
                Spacer(modifier = Modifier.height(height = 16.dp))
            }
        }
    }
}

