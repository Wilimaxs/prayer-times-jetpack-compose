package com.selfproject.prayertime.ui.feature.locationFind

import androidx.lifecycle.ViewModel
import com.selfproject.prayertime.ui.feature.locationFind.components.SearchLocationText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class LocationFindViewModel @Inject constructor(

) : ViewModel() {

    private val _searchLocationText = MutableStateFlow(value = SearchLocationText())
    val searchLocationText = _searchLocationText.asStateFlow()

    fun searchLocation(location: String) {
        _searchLocationText.update {
            it.copy(searchLocation = location)
        }
    }
}