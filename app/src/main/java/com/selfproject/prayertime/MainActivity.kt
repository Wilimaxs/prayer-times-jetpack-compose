package com.selfproject.prayertime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.selfproject.prayertime.ui.feature.mainNavigation.MainNavigationScreen
import com.selfproject.prayertime.ui.theme.PrayerTimeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrayerTimeTheme {
                MainNavigationScreen()
            }
        }
    }
}