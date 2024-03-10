package com.gilberto.test.features.home.description

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilberto.domain.usecase.ObserveMovementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class MovementDetailViewModel @Inject constructor(
    val observeMovementUseCase: ObserveMovementUseCase,
) : ViewModel() {
    private val internalUiState = MutableStateFlow(MovementDetailUiState())
    val uiState: StateFlow<MovementDetailUiState> = internalUiState

    fun getMovement(movementId: String) {
        viewModelScope.launch {
            observeMovementUseCase(ObserveMovementUseCase.Params(movementId)).catch {

            }.collect {
                internalUiState.value = uiState.value.copy(
                    movement = it
                )
            }
        }
    }
}
