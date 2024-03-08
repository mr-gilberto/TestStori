package com.gilberto.test.features.login

import androidx.annotation.StringRes

data class LoginState(
    val loading: Boolean = false,
    val errorState: ErrorState? = null,
    val navigateToMainView: Boolean = false,
    val navigateToRegister: Boolean = false,
)

data class ErrorState(
    @StringRes val emailError: Int? = null,
    @StringRes val passwordError: Int? = null,
    @StringRes val snackError: Int? = null,
)
