package com.selfproject.prayertime.ui.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.selfproject.prayertime.ui.theme.GlassBorderColor
import com.selfproject.prayertime.ui.theme.PrimaryBlue
import com.selfproject.prayertime.ui.theme.TextWhite
import com.selfproject.prayertime.ui.theme.TextWhiteSecondary


// Composable Date
@Composable
fun HomeDate(
    dateText: String
) {
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
            dateText,
            color = TextWhite.copy(alpha = 0.9f),
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}

// Composable Location
@Composable
fun HomeLocation() {
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