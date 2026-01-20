package com.selfproject.prayertime.ui.feature.locationFind.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.selfproject.prayertime.ui.theme.PrimaryBlue

@Composable
fun CheckLocationCard(
    onCheckLocation: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onCheckLocation)
            .clip(shape = RoundedCornerShape(size = 16.dp))
            .background(color = Color(color = 0x66334455))
            .padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(size = 44.dp)
                .clip(shape = CircleShape)
                .background(color = PrimaryBlue.copy(alpha = 0.8f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.MyLocation,
                contentDescription = "Current Location",
                tint = Color(color = 0xFFFFBB86),
                modifier = Modifier.size(size = 20.dp)
            )
        }
        Spacer(modifier = Modifier.width(width = 16.dp))
        Text(
            text = "Use current location",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            letterSpacing = 0.02.sp
        )
    }
}

@Preview
@Composable
fun CheckLocationCardPreview() {
    CheckLocationCard(onCheckLocation = {})
}