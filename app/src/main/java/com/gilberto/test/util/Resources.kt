package com.gilberto.test.util

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import com.gilberto.test.BaseApplication

fun appStringResource(@StringRes id: Int?): String? =
    id?.let { BaseApplication.application.resources.getString(it) }

fun appStringResource(@StringRes id: Int): String =
    BaseApplication.application.resources.getString(id)

fun appStringArrayResource(@ArrayRes id: Int): Array<String> =
    BaseApplication.application.resources.getStringArray(id)

fun appIntArrayResource(@ArrayRes id: Int): IntArray =
    BaseApplication.application.resources.getIntArray(id)
