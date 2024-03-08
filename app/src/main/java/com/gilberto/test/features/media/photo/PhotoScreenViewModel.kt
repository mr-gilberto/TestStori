package com.gilberto.test.features.media.photo

import android.net.Uri
import androidx.camera.core.CameraInfo
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gilberto.test.features.media.photo.utils.UtilMedia.CAPTURE_FAIL
import com.gilberto.test.features.media.photo.utils.UtilMedia.PHOTO_EXTENSION_JPG
import com.gilberto.test.features.media.photo.utils.UtilMedia.TAG
import com.gilberto.test.features.media.photo.utils.UtilMedia.TEMPORAL_FILE_DIRECTORY
import com.gilberto.test.features.media.photo.utils.UtilMedia.TIMER_10S
import com.gilberto.test.features.media.photo.utils.UtilMedia.TIMER_3S
import com.gilberto.test.features.media.photo.utils.UtilMedia.TIMER_OFF
import com.gilberto.test.features.media.photo.models.PhotoEvent
import com.gilberto.test.features.media.photo.models.PhotoState
import com.gilberto.test.features.media.photo.utils.FileManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class PhotoScreenViewModel @Inject constructor(
    private val fileManager: FileManager,
) : ViewModel() {

    private val internalPhotoState: MutableStateFlow<PhotoState> = MutableStateFlow(PhotoState())
    val uiState: StateFlow<PhotoState> = internalPhotoState

    fun onEvent(photoEvent: PhotoEvent) {
        when (photoEvent) {
            PhotoEvent.DelayTimerTapped -> {
                onDelayTimerTapped()
            }

            PhotoEvent.FlashTapped -> onFlashTapped()
            PhotoEvent.FlipTapped -> onFlipTapped()

            is PhotoEvent.CaptureTapped -> onCaptureTapped(
                photoEvent.timeMillis,
                photoEvent.photoCaptureManager,
            )

            is PhotoEvent.CameraInitialized -> onCameraInitialized(photoEvent.cameraLensInfo)
            is PhotoEvent.ImageCaptured -> onImageCaptured(photoEvent.imageResult.savedUri)
            is PhotoEvent.SwitchToVideo -> Unit
            is PhotoEvent.OnCloseTapped -> {
                internalPhotoState.value = internalPhotoState.value.copy(
                    onCloseTapped = true,
                )
            }

            else -> Unit
        }
    }

    private fun onCaptureTapped(timeMillis: Long, photoCaptureManager: PhotoCaptureManager) =
        viewModelScope.launch {
            delay(timeMillis)
            try {
                val filePath = fileManager.createTemporalFile(TEMPORAL_FILE_DIRECTORY, PHOTO_EXTENSION_JPG)
                photoCaptureManager.takePhoto(
                    filePath,
                    internalPhotoState.value.lens
                        ?: CameraSelector.LENS_FACING_BACK,
                )
            } catch (exception: IllegalArgumentException) {
                Timber.e(TAG, exception.localizedMessage ?: CAPTURE_FAIL)
            }
        }

    private fun onDelayTimerTapped() = viewModelScope.launch {
        internalPhotoState.value = when (internalPhotoState.value.delayTimer) {
            TIMER_OFF -> internalPhotoState.value.copy(delayTimer = TIMER_3S)
            TIMER_3S -> internalPhotoState.value.copy(delayTimer = TIMER_10S)
            TIMER_10S -> internalPhotoState.value.copy(delayTimer = TIMER_OFF)
            else -> internalPhotoState.value.copy(delayTimer = TIMER_OFF)
        }
    }

    private fun onFlashTapped() = viewModelScope.launch {
        internalPhotoState.value = when (internalPhotoState.value.flashMode) {
            ImageCapture.FLASH_MODE_OFF -> internalPhotoState.value.copy(flashMode = ImageCapture.FLASH_MODE_AUTO)
            ImageCapture.FLASH_MODE_AUTO -> internalPhotoState.value.copy(flashMode = ImageCapture.FLASH_MODE_ON)
            ImageCapture.FLASH_MODE_ON -> internalPhotoState.value.copy(flashMode = ImageCapture.FLASH_MODE_OFF)
            else -> internalPhotoState.value.copy(flashMode = ImageCapture.FLASH_MODE_OFF)
        }
    }

    private fun onFlipTapped() = viewModelScope.launch {
        val lens = if (internalPhotoState.value.lens == CameraSelector.LENS_FACING_FRONT) {
            CameraSelector.LENS_FACING_BACK
        } else {
            CameraSelector.LENS_FACING_FRONT
        }
        // Check if the lens has flash unit
        val flashMode = if (internalPhotoState.value.lensInfo[lens]?.hasFlashUnit() == true) {
            internalPhotoState.value.flashMode
        } else {
            ImageCapture.FLASH_MODE_OFF
        }
        if (internalPhotoState.value.lensInfo[lens] != null) {
            internalPhotoState.value = internalPhotoState.value.copy(lens = lens, flashMode = flashMode)
        }
    }

    private fun onImageCaptured(uri: Uri?) = viewModelScope.launch {
        if (uri != null && uri.path != null) {
            internalPhotoState.value = internalPhotoState.value.copy(
                latestImageUri = uri,
                successPhotoTaken = true,
            )
        } else {
            val mediaDir = fileManager.getPrivateFileDirectory(TEMPORAL_FILE_DIRECTORY)
            val latestImageUri = mediaDir?.listFiles()?.lastOrNull()?.toUri() ?: Uri.EMPTY
            internalPhotoState.value = internalPhotoState.value.copy(
                latestImageUri = latestImageUri,
                successPhotoTaken = true,
            )
        }
    }

    private fun onCameraInitialized(cameraLensInfo: HashMap<Int, CameraInfo>) =
        viewModelScope.launch {
            if (cameraLensInfo.isNotEmpty()) {
                val defaultLens = if (cameraLensInfo[CameraSelector.LENS_FACING_BACK] != null) {
                    CameraSelector.LENS_FACING_BACK
                } else if (cameraLensInfo[CameraSelector.LENS_FACING_BACK] != null) {
                    CameraSelector.LENS_FACING_FRONT
                } else {
                    null
                }
                internalPhotoState.value = internalPhotoState.value
                    .copy(lens = internalPhotoState.value.lens ?: defaultLens, lensInfo = cameraLensInfo)
            }
        }

    fun onSuccessPhotoTaken() {
        internalPhotoState.value = internalPhotoState.value.copy(
            successPhotoTaken = false,
        )
    }
}
