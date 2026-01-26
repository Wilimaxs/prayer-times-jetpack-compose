package com.selfproject.prayertime.ui.feature.mainNavigation

sealed class Screen(val route: String) {
    object Home : Screen("home?lat={lat}&long={long}") {
        fun createRoute(lat: Double, long: Double): String {
            return "home?lat=$lat&long=$long"
        }
    }
    object Quran : Screen("Quran")
    object Qibla : Screen("Qibla")
    object Settings : Screen("settings")
    object Location : Screen("home/location")
}