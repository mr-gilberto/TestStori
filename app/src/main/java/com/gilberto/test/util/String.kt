package com.gilberto.test.util

fun String?.orDefault(defaultValue: String): String {
    return this ?: defaultValue
}

fun String?.orEmpty(): String {
    return this ?: ""
}

fun String.isValidMail(): Boolean {
        val pattern = Regex("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\$")
        return pattern.matches(this)
}
