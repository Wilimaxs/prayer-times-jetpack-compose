package com.selfproject.prayertime.ui.feature.mainNavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.selfproject.prayertime.ui.feature.home.HomeScreen
import com.selfproject.prayertime.ui.feature.locationFind.LocationFindScreen

data class BottomNavItem(
    val name: String, val route: String, val icon: ImageVector
)

@Composable
fun MainNavigationScreen() {
    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem(name = "Home", route = Screen.Home.route, icon = Icons.Default.Home),
        BottomNavItem(
            name = "Qur'an", route = Screen.Quran.route, icon = Icons.AutoMirrored.Filled.MenuBook
        ),
        BottomNavItem(name = "Qibla", route = Screen.Qibla.route, icon = Icons.Default.Explore),
        BottomNavItem(
            name = "Settings", route = Screen.Settings.route, icon = Icons.Default.Settings
        ),
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    val showBottomBar = currentRoute?.route in items.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    modifier = Modifier.shadow(
                        elevation = 8.dp,
                        spotColor = Color.Black,
                        ambientColor = Color.Black,
                    ),
                    containerColor = Color(0xFF102222),
                    contentColor = Color(0xFFFFFF66),
                ) {
                    items.forEach { item ->
                        val isSelected =
                            currentRoute?.hierarchy?.any { it.route == item.route } == true
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    item.icon, contentDescription = item.name
                                )
                            },
                            label = { Text(item.name) },
                            selected = isSelected,
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF102222),
                                selectedTextColor = Color(0xFFEEBB44),
                                indicatorColor = Color(0xFFEEBB44),
                                unselectedIconColor = Color.White.copy(alpha = 0.6f),
                                unselectedTextColor = Color.White.copy(alpha = 0.6f)
                            ),
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            })
                    }
                }
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            Modifier.padding(
                bottom =
                    if (showBottomBar) innerPadding.calculateBottomPadding()
                    else 0.dp
            )
        ) {
            // 1. Tab Home
            composable(
                route = Screen.Home.route,
                arguments = listOf(
                    navArgument("lat") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    },
                    navArgument("long") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) {
                HomeScreen(
                    navigateToLocationPage = { navController.navigate(Screen.Location.route) })
            }

            composable(Screen.Location.route) {
                LocationFindScreen(
                    onBackPressed = {
                        navController.popBackStack()
                    },

                    onLocationPicked = { lat, long ->
                        val route = Screen.Home.createRoute(lat, long)
                        navController.navigate(route) {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            // 2. Tab Qibla (Placeholder dulu)
            composable(Screen.Qibla.route) {
                PlaceholderScreen("Qibla")
            }

            // 3. Tab Quran (Placeholder dulu)
            composable(Screen.Quran.route) {
                PlaceholderScreen("Quran")
            }
            // 4. Tab settings (Placeholder dulu)
            composable(Screen.Settings.route) {
                PlaceholderScreen("Settings")
            }
        }
    }
}

@Composable
fun PlaceholderScreen(title: String) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(text = "Halaman $title Belum Dibuat")
    }
}