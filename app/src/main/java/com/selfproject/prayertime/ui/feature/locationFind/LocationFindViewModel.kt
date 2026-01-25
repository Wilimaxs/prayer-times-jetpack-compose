package com.selfproject.prayertime.ui.feature.locationFind

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class LocationFindViewModel @Inject constructor(

) : ViewModel() {

    private val _locationFindState =
        MutableStateFlow(value = LocationFindScreenState())
    val locationFindState = _locationFindState.asStateFlow()

    init {
        getPopularLocation()
    }

    fun searchLocation(location: String) {
        _locationFindState.update {
            it.copy(
                searchLocationText = it.searchLocationText.copy(searchLocation = location)
            )
        }
    }


    private fun getPopularLocation() {
        return _locationFindState.update {
            it.copy(
                locationItems = listOf(
                    LocationItem(city = "Jakarta", country = "Indonesia"),
                    LocationItem(city = "Bandung", country = "Indonesia"),
                    LocationItem(city = "Surabaya", country = "Indonesia"),
                    LocationItem(city = "Medan", country = "Indonesia"),
                )
            )
        }
    }
}