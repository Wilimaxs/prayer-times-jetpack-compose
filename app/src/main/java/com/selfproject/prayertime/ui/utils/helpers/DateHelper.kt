package com.selfproject.prayertime.ui.utils.helpers

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateHelper {
    fun getCurrentDate(): String {
        return runCatching {
            val formattedDate =
                DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy", Locale.forLanguageTag("id-ID"))
            LocalDate.now().format(formattedDate)
        }.getOrDefault("Jadwal Sholat")
    }
}