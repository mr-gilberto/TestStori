package com.gilberto.test.features.register

import androidx.annotation.StringRes

data class RegisterState(
    val loading: Boolean = false,
    val errorState: ErrorState? = null,
    val navigateToMainView: Boolean = false,
)

data class ErrorState(
    @StringRes val snackError: Int? = null,
)