package com.gilberto.test.util

import java.text.SimpleDateFormat
import java.util.Date


fun convertToDateFormat(miliseconds: Long): String {
    return try {
        val formato = SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
        val fecha = Date(miliseconds)
        formato.format(fecha)
    } catch (exception: Exception) {
        ""
    }
}
