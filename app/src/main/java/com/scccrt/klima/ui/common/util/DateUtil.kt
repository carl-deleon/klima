package com.scccrt.klima.ui.common.util

import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Locale

object DateUtil {
    fun Long.toFormattedDate(dateFormat: String = "MMMM dd, yyyy - hh:mm a"): String {
        val outputDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        try {
            return outputDateFormat.format(this * 1000L)
        } catch (e: Exception) {
            Timber.e(e)
        }
        return ""
    }

    fun Long.toFormattedTime(timeFormat: String = "hh:mm a"): String {
        val outputTimeFormat = SimpleDateFormat(timeFormat, Locale.getDefault())
        try {
            return outputTimeFormat.format(this * 1000L)
        } catch (e: Exception) {
            Timber.e(e)
        }
        return ""
    }
}