package com.gilberto.test.features.home.main

import com.gilberto.domain.models.MovementEntity
import com.gilberto.domain.models.UserEntity

data class HomeState(
    val loading: Boolean = false,
    val syncFailed: Boolean = false,
    val networkNotAvailable: Boolean = false,
    val logOut: Boolean = false,
    val movements: List<MovementEntity> = emptyList(),
    val snackBarMessage: String? = null,
    val user: UserEntity?  = null,
)