package com.selfproject.prayertime.ui.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selfproject.prayertime.data.common.Resource
import com.selfproject.prayertime.data.respository.PrayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PrayerRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)

    val uiState = _uiState.asStateFlow()

    init {
        getPrayerTimes("Jakarta", "Indonesia")
    }

    fun getPrayerTimes(city: String, country: String) {
        viewModelScope.launch {
            repository.getTimingsByCity(city, country)
                .collect { result ->
                    _uiState.value = when (result) {
                        is Resource.Loading -> HomeUiState.Loading
                        is Resource.Success -> {
                            if (result.data != null) {
                                HomeUiState.Success(result.data)
                            } else {
                                HomeUiState.Error("No data found")
                            }
                        }

                        is Resource.Error -> {
                            HomeUiState.Error(result.message ?: "Something went wrong")
                        }
                    }
                }
        }
    }
}