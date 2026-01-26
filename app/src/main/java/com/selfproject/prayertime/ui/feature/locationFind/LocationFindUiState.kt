package com.selfproject.prayertime.ui.feature.locationFind


data class SearchLocationText(
    val searchLocation: String = ""
)

data class LocationItem(
    val city: String,
    val province: String,
    val lat: Double,
    val long: Double
)

data class LocationFindScreenState(
    val searchLocationText: SearchLocationText = SearchLocationText(),
    val locationItems: List<LocationItem> = emptyList(),
    val checkCurrentLocation: Boolean = false
)