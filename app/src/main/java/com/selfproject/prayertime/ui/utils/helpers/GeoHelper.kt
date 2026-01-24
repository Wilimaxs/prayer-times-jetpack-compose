package com.selfproject.prayertime.ui.utils.helpers

import android.content.Context
import android.location.Geocoder
import android.os.Build.VERSION.SDK_INT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.Locale
import kotlin.coroutines.resume

@Suppress("DEPRECATION")
suspend fun getLocationName(context: Context, lat: Double, long: Double): String {
    return withContext(Dispatchers.IO) {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            if (SDK_INT >= 33) {
                suspendCancellableCoroutine { continuation ->
                    geocoder.getFromLocation(lat, long, 1) { addresses ->
                        val address = listOfNotNull(
                            addresses.firstOrNull()?.subLocality,
                            addresses.firstOrNull()?.locality,
                            addresses.firstOrNull()?.adminArea
                        ).joinToString(", ")
                        Timber.d("Location address: $address")
                        if (continuation.isActive) {
                            continuation.resume(address)
                        }
                    }
                }
            } else {
                val addresses = geocoder.getFromLocation(lat, long, 1)
                val address = listOfNotNull(
                    addresses?.firstOrNull()?.subLocality,
                    addresses?.firstOrNull()?.locality,
                    addresses?.firstOrNull()?.adminArea
                ).joinToString(", ")
                Timber.d("Location address: $address")
                address
            }
        } catch (e: Exception) {
            Timber.e(e, "Error geocoding")
            ""
        }
    }
}