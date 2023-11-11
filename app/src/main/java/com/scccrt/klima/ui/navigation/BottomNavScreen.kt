package com.scccrt.klima.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.scccrt.klima.R

sealed class BottomNavScreen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    data object Current : BottomNavScreen("current", R.string.current, Icons.Filled.Place)
    data object Forecast : BottomNavScreen("forecast", R.string.forecast, Icons.AutoMirrored.Filled.List)
}