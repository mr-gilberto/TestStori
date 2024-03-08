package com.gilberto.test.features.media.photo.models

import android.net.Uri
import androidx.camera.core.CameraInfo
import androidx.camera.core.ImageCapture
import com.gilberto.test.features.media.photo.utils.UtilMedia

data class PhotoState(
    val cameraState: CameraState = CameraState.NOT_READY,
    val captureWithDelay: Long = 0,
    val delayTimer: Int = UtilMedia.TIMER_OFF,
    @ImageCapture.FlashMode val flashMode: Int = ImageCapture.FLASH_MODE_OFF,
    val latestImageUri: Uri? = null,
    val lens: Int? = null,
    val lensInfo: MutableMap<Int, CameraInfo> = mutableMapOf(),
    val successPhotoTaken: Boolean = false,
    val onCloseTapped: Boolean = false,
)

enum class CameraState {
    /**
     * Camera hasn't been initialized.
     */
    NOT_READY,

    /**
     * Camera is open and presenting a preview stream.
     */
    READY,

    /**
     * Some values changed on camera state.
     */
    CHANGED,
}
