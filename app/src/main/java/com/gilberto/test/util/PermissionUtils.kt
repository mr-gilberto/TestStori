package com.gilberto.test.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager

fun Context.hasWriteStoragePermission(): Boolean {
    val permission =
        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    return permission == PackageManager.PERMISSION_GRANTED
}

fun Context.hasReadStoragePermission(): Boolean {
    val permission =
        checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    return permission == PackageManager.PERMISSION_GRANTED
}
