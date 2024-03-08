package com.gilberto.test.features.register

sealed class RegistrationFormEvent {
    data class NameChange(val name: String) : RegistrationFormEvent()
    data class SurnameChange(val surname: String) : RegistrationFormEvent()
    data class EmailChanged(val email: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String) : RegistrationFormEvent()
    data object Register : RegistrationFormEvent()
    data object OnSnackbarDismiss: RegistrationFormEvent()
}

data class RegistrationFormState(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
)