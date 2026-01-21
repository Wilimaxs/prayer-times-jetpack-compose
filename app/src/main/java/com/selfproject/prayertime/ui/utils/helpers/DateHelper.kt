package com.selfproject.prayertime.ui.utils.helpers

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateHelper {
    fun getCurrentDate(withDayName: Boolean = true): String {
        return runCatching {
            val pattern = if (withDayName) {
                "EEEE, dd MMMM yyyy"
            } else {
                "dd-MM-yyyy"
            }
            val formattedDate =
                DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)
            LocalDate.now().format(formattedDate)
        }.getOrDefault(
            defaultValue = if (withDayName) {
                "Monday, 01 January 2026"
            } else {
                "01-01-2026"
            }
        )
    }
}