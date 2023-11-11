package com.scccrt.klima.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.scccrt.klima.ui.navigation.destination.CurrentWeatherDestination
import com.scccrt.klima.ui.navigation.destination.WeatherForecastDestination

@Composable
fun AppNavigation() {

    var navigationItemSelected by remember { mutableIntStateOf(0) }
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            KlimaBottomNavigationBar(
                bottomNavigationItems = listOf(
                    BottomNavScreen.Current,
                    BottomNavScreen.Forecast
                ),
                navigationItemSelected
            ) { navigationItem, position ->
                navigationItemSelected = position

                navController.navigate(navigationItem.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Navigation.Routes.CURRENT,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(
                route = Navigation.Routes.CURRENT
            ) {
                CurrentWeatherDestination()
            }
            composable(
                route = Navigation.Routes.FORECAST
            ) {
                WeatherForecastDestination()
            }
        }
    }
}

object Navigation {

    object Routes {
        const val CURRENT = "current"
        const val FORECAST = "forecast"
    }
}