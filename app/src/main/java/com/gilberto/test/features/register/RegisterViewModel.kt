package com.gilberto.test.features.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilberto.domain.usecase.RegisterUseCase
import com.gilberto.test.R
import com.gilberto.test.util.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val networkManager: NetworkManager,
) : ViewModel() {

    private val internalUiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = internalUiState

    var registerFormState by mutableStateOf(RegistrationFormState())
        private set

    fun register(registerState: RegistrationFormState) {
        if (networkManager.isNetworkAvailable()) {
            internalUiState.value = internalUiState.value.copy(
                loading = true,
                errorState = null,
            )

            if (isValidForm(registerState)) {
                viewModelScope.launch {
                    val user = registerState.email.trim()
                    val password = registerState.password.trim()

                    registerUseCase(RegisterUseCase.Params(user, password))
                        .onSuccess {
                            internalUiState.value = internalUiState.value.copy(
                                navigateToMainView = true,
                                errorState = null,
                            )
                        }.onFailure {
                            internalUiState.value = internalUiState.value.copy(
                                navigateToMainView = false,
                                errorState = ErrorState(snackError = R.string.error_invalid_login),
                            )
                        }
                }
            }
        } else {
            internalUiState.value = internalUiState.value.copy(
                navigateToMainView = false,
                errorState = ErrorState(snackError = R.string.not_network_available),
            )
        }
    }

    private fun isValidForm(registerState: RegistrationFormState): Boolean {
        val invalidName = registerState.name.isBlank()
        val invalidSurname = registerState.surname.isBlank()
        val invalidEmail = registerState.email.isBlank()
        val invalidPassword = registerState.password.isBlank()
        val invalidRepeatedPassword = registerState.repeatedPassword.isBlank()

        val errorMessage = when {
            invalidName -> R.string.error_invalid_name
            invalidSurname -> R.string.error_invalid_surname
            invalidEmail -> R.string.error_invalid_email
            invalidPassword -> R.string.error_invalid_password
            invalidRepeatedPassword -> R.string.error_invalid_repeated_password
            registerState.password != registerState.repeatedPassword -> {
                R.string.error_invalid_not_match_password
            }

            else -> null
        }

        internalUiState.update { currentState ->
            currentState.copy(
                loading = false,
                errorState = ErrorState(snackError = errorMessage),
            )
        }

        return errorMessage == null
    }

    fun onNavigate() {
        internalUiState.value = internalUiState.value.copy(navigateToMainView = false)
    }

    fun onViewEvent(event: RegistrationFormEvent) {
        internalUiState.value = internalUiState.value.copy(
            errorState = null,
        )

        when (event) {
            is RegistrationFormEvent.NameChange -> {
                registerFormState = registerFormState.copy(name = event.name)
            }

            is RegistrationFormEvent.SurnameChange -> {
                registerFormState = registerFormState.copy(surname = event.surname)
            }

            is RegistrationFormEvent.EmailChanged -> {
                registerFormState = registerFormState.copy(email = event.email)
            }

            is RegistrationFormEvent.PasswordChanged -> {
                registerFormState = registerFormState.copy(password = event.password)
            }

            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                registerFormState = registerFormState.copy(repeatedPassword = event.repeatedPassword)
            }

            is RegistrationFormEvent.Register -> {
                register(registerFormState)
            }

            RegistrationFormEvent.OnSnackbarDismiss -> {
                internalUiState.value = internalUiState.value.copy(
                    errorState = null,
                )
            }
        }
    }
}