package com.gilberto.test.features.home.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilberto.domain.common.base.AppDispatchers
import com.gilberto.domain.usecase.GetUserUseCase
import com.gilberto.domain.usecase.LogOutUseCase
import com.gilberto.domain.usecase.ObserveMovementsUseCase
import com.gilberto.test.R
import com.gilberto.test.util.NetworkManager
import com.gilberto.test.util.appStringResource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkManager: NetworkManager,
    private val appDispatchers: AppDispatchers,
    private val observeMovements: ObserveMovementsUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val getUserUseCase: GetUserUseCase,
) : ViewModel() {

    private val internalUiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = internalUiState

    init {
        getMovements()
        getUser()

    }

    private fun getUser() {
        viewModelScope.launch {
            getUserUseCase().onSuccess {
                internalUiState.value = internalUiState.value.copy(
                    user = it
                )
            }
        }
    }

    private fun getMovements() {
        viewModelScope.launch {
            internalUiState.value = internalUiState.value.copy(
                loading = true,
                syncFailed = false,
                networkNotAvailable = false,
            )
            if (networkManager.isNetworkAvailable()) {
                observeMovements()
                    .catch {
                        internalUiState.value = internalUiState.value.copy(
                            loading = false,
                            syncFailed = true,
                        )
                    }
                    .collect {
                        internalUiState.value = internalUiState.value.copy(
                            loading = false,
                            movements = it
                        )
                    }
            } else {
                internalUiState.value = internalUiState.value.copy(
                    loading = false,
                    networkNotAvailable = true,
                    snackBarMessage = appStringResource(R.string.not_network_available),
                )

                hideSnackBarMessage()
            }
        }
    }


    fun logOut() {
        viewModelScope.launch {
            logOutUseCase().onSuccess { logOut ->
                internalUiState.value = internalUiState.value.copy(
                    loading = false,
                    logOut = logOut,
                )
            }.onFailure {
                internalUiState.value = internalUiState.value.copy(
                    loading = false,
                    logOut = false,
                )
            }
        }
    }


    private fun hideSnackBarMessage() = viewModelScope.launch(appDispatchers.IO) {
        delay(3000)
        internalUiState.value = internalUiState.value.copy(
            snackBarMessage = null,
        )
    }
}
