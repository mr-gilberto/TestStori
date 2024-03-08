package com.gilberto.test.features.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilberto.domain.usecase.LoginUseCase
import com.gilberto.test.R
import com.gilberto.test.util.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val networkManager: NetworkManager,
) : ViewModel() {

    private val internalUiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = internalUiState

    fun login(userName: String, userPassword: String) {
        if (networkManager.isNetworkAvailable()) {
            internalUiState.value = internalUiState.value.copy(
                loading = true,
                errorState = null,
            )

            if (isValidForm(userName, userPassword)) {
                viewModelScope.launch {
                    val user = userName.trim()
                    val password = userPassword.trim()

                    loginUseCase(LoginUseCase.Params(user, password))
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

    private fun isValidForm(userName: String, userPassword: String): Boolean {
        val invalidUser = userName.isBlank()
        val invalidPassword = userPassword.isBlank()

        val errorMessage = when {
            invalidUser -> R.string.error_invalid_user
            invalidPassword -> R.string.error_invalid_password
            else -> null
        }

        internalUiState.update { currentState ->
            currentState.copy(
                loading = false,
                errorState = ErrorState(snackError = errorMessage),
            )
        }

        return !invalidUser && !invalidPassword
    }

    fun onActionSnackBar() {
        internalUiState.value = internalUiState.value.copy(errorState = null)
    }

    fun onNavigate() {
        internalUiState.value = internalUiState.value.copy(navigateToMainView = false)
    }
}
