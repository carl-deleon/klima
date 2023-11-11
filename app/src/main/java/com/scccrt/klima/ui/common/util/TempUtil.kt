package com.scccrt.klima.ui.common.util

import java.math.RoundingMode
import java.text.DecimalFormat

object TempUtil {

    fun Double.toCelcius(): String {
        val celcius = this - 273.15

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(celcius).toString()
    }
}