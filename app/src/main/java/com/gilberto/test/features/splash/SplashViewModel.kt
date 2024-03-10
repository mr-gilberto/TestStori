package com.gilberto.test.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilberto.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val internalUiState = MutableStateFlow(SplashState())
    val uiState: StateFlow<SplashState> = internalUiState

    fun observeUserInfo() {
        internalUiState.value = internalUiState.value.copy(
            isLoggedIn = false,
        )

        viewModelScope.launch {
            getUserUseCase()
                .onSuccess { _ ->
                    internalUiState.value = internalUiState.value.copy(
                        isLoggedIn = true,
                    )
                }
                .onFailure { _ ->
                    internalUiState.value = internalUiState.value.copy(
                        isLoggedIn = false,
                    )
                }
        }
    }
}
