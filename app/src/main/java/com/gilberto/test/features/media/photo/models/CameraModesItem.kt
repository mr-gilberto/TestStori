package com.gilberto.test.features.media.photo.models

data class CameraModesItem(
    val cameraMode: Int,
    val name: String,
    val selected: Boolean = false,
)
