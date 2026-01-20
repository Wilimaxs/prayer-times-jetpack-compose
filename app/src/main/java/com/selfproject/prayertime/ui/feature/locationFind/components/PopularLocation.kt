package com.selfproject.prayertime.ui.feature.locationFind.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.selfproject.prayertime.ui.theme.TextWhite
import com.selfproject.prayertime.ui.utils.reusable.ButtonCard

@Composable
fun PopularLocation() {
    val popularLocations = listOf(
        LocationItem(city = "Jakarta", country = "Indonesia"),
        LocationItem(city = "Bandung", country = "Indonesia"),
        LocationItem(city = "Surabaya", country = "Indonesia"),
        LocationItem(city = "Medan", country = "Indonesia"),
    )
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
                country = locationItem.country,
                onButtonClicked = {}
            )
            if (index < popularLocations.lastIndex) {
                Spacer(modifier = Modifier.height(height = 16.dp))
            }
        }
    }
}

@Preview
@Composable
fun PopularLocationPreview() {
    PopularLocation()
}

