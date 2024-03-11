package com.gilberto.test.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun convertToDateFormat(miliseconds: Long): String {
    return try {
        val formato = SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.getDefault())
        val fecha = Date(miliseconds)
        formato.format(fecha)
    } catch (exception: Exception) {
        ""
    }
}
