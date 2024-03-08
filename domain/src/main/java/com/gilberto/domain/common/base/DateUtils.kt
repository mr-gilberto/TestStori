package com.gilberto.domain.common.base

import java.util.Calendar

object DateUtils {

    fun getCurrentDateTime(): Long {
        return Calendar.getInstance().timeInMillis
    }
}
