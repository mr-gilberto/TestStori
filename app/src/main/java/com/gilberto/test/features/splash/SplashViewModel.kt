package com.gilberto.test.features.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilberto.domain.usecase.ObserveMovements
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val observeUserInfoUseCase: ObserveMovements,
) : ViewModel() {

    private val internalUiState = MutableStateFlow(SplashState())
    val uiState: StateFlow<SplashState> = internalUiState

    fun observeUserInfo() {
        internalUiState.value = internalUiState.value.copy(
            isLoggedIn = false,
        )

        viewModelScope.launch {
            observeUserInfoUseCase()
                .catch { _ ->
                    internalUiState.value = internalUiState.value.copy(
                        isLoggedIn = false,
                    )
                }
                .collectLatest { _ ->
                    internalUiState.value = internalUiState.value.copy(
                        isLoggedIn = true,
                    )
                }
        }
    }
}
