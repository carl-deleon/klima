package com.scccrt.klima.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.scccrt.klima.R

sealed class BottomNavScreen(val route: String, @StringRes val resourceId: Int, @DrawableRes val icon: Int) {
    data object Current : BottomNavScreen("current", R.string.current, R.drawable.ic_weather)
    data object Forecast : BottomNavScreen("forecast", R.string.forecast, R.drawable.ic_weather_forecast)
}