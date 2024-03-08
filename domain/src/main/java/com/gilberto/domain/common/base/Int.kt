package com.gilberto.domain.common.base

fun Int?.toStringOrEmpty(): String {
    return this?.toString() ?: ""
}

fun Long?.toStringOrEmpty(): String {
    return this?.toString() ?: ""
}

fun Double?.toStringOrEmpty(): String {
    return this?.toString() ?: ""
}
