package com.gilberto.test.features.media.photo.models

import androidx.camera.core.CameraInfo
import androidx.camera.core.ImageCapture
import com.gilberto.test.features.media.photo.PhotoCaptureManager

sealed class PhotoEvent {
    data class CameraInitialized(val cameraLensInfo: HashMap<Int, CameraInfo>) : PhotoEvent()
    data class ImageCaptured(val imageResult: ImageCapture.OutputFileResults) : PhotoEvent()
    data class CaptureTapped(
        val timeMillis: Long = 0L,
        val photoCaptureManager: PhotoCaptureManager,
    ) : PhotoEvent()

    object SwitchToVideo : PhotoEvent()
    object DelayTimerTapped : PhotoEvent()
    object FlashTapped : PhotoEvent()
    object FlipTapped : PhotoEvent()
    object OnCloseTapped : PhotoEvent()
}
