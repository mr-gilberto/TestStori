package com.gilberto.test.features.media

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilberto.domain.usecase.UploadFileUseCase
import com.gilberto.test.R
import com.gilberto.test.util.NetworkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TakePictureViewModel @Inject constructor(
    private val uploadFileUseCase: UploadFileUseCase,
    private val networkManager: NetworkManager,
) : ViewModel() {

    private val internalUiState = MutableStateFlow(TakePictureState())
    val uiState: StateFlow<TakePictureState> = internalUiState

    fun uploadFile(fileUri: String) {
        if (networkManager.isNetworkAvailable()) {
            internalUiState.value = internalUiState.value.copy(
                loading = true,
                errorState = null,
            )

            viewModelScope.launch {
                uploadFileUseCase(UploadFileUseCase.Params(fileUri)).onSuccess {
                    internalUiState.value = internalUiState.value.copy(
                        loading = false, errorState = null, successTakePicture = true
                    )
                }.onFailure {
                    internalUiState.value = internalUiState.value.copy(
                        successTakePicture = false,
                        errorState = ErrorState(snackError = R.string.generic_error),
                    )
                }
            }
        } else {
            internalUiState.value = internalUiState.value.copy(
                successTakePicture = false,
                errorState = ErrorState(snackError = R.string.not_network_available),
            )
        }
    }

    fun onNavigate() {
        internalUiState.value = internalUiState.value.copy(
            successTakePicture = false,
        )
    }
}
