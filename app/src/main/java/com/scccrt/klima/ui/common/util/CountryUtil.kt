package com.scccrt.klima.ui.common.util

import java.util.Locale

object CountryUtil {

    fun String.asCountryName(): String {
        return Locale("", this).displayCountry
    }
}