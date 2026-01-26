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

    fun setRequestLocation(needRequest: Boolean) {
        _locationFindState.update {
            it.copy(
                checkCurrentLocation = needRequest
            )
        }
    }

    fun searchLocation(location: String) {
        _locationFindState.update {
            it.copy(
                searchLocationText = it.searchLocationText.copy(searchLocation = location)
            )
        }
    }


    private fun getPopularLocation() {
        _locationFindState.update { state ->
            state.copy(
                locationItems = listOf(
                    LocationItem(
                        city = "Jakarta Pusat",
                        province = "DKI Jakarta",
                        lat = -6.2088,
                        long = 106.8456
                    ),
                    LocationItem(
                        city = "Bandung",
                        province = "Jawa Barat",
                        lat = -6.9175,
                        long = 107.6191
                    ),
                    LocationItem(
                        city = "Surabaya",
                        province = "Jawa Timur",
                        lat = -7.2575,
                        long = 112.7521
                    ),
                    LocationItem(
                        city = "Medan",
                        province = "Sumatera Utara",
                        lat = 3.5952,
                        long = 98.6722
                    ),
                    LocationItem(
                        city = "Yogyakarta",
                        province = "DI Yogyakarta",
                        lat = -7.7956,
                        long = 110.3695
                    )
                )
            )
        }
    }
}