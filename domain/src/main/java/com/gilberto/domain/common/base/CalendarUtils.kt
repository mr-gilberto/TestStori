package com.gilberto.domain.common.base

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object CalendarUtils {

    const val LAST_SYNC_FORMAT = "dd MMMM yyyy hh:mm a"


    fun combineDateAndTime(dateService: Long?, hourService: String?): String {
        if (dateService == null) {
            return ""
        }

        return try {
            val date = Date(dateService)
            val timeFormatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            val timeDate = timeFormatter.parse(hourService)
            val combinedDateTime = SimpleDateFormat(LAST_SYNC_FORMAT, Locale.getDefault())
                .format(Date(date.time + timeDate.time))
            combinedDateTime
        } catch (exception: Exception) {
            exception.printStackTrace()
            ""
        }
    }

    fun formatMillisToDateString(millis: Long, format: String): String {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis
        return sdf.format(calendar.time)
    }
}
