package com.gilberto.test.util

fun String?.orDefault(defaultValue: String): String {
    return this ?: defaultValue
}

fun String?.orEmpty(): String {
    return this ?: ""
}
