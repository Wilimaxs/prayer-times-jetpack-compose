package com.selfproject.prayertime.ui.feature.mainNavigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Quran : Screen("Quran")
    object Qibla : Screen("Qibla")
    object Settings : Screen("settings")
    object Location : Screen("home/location")
}