package com.gilberto.test.features.media.photo.utils

import android.net.Uri
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import android.view.HapticFeedbackConstants
import android.view.Surface
import android.view.View
import androidx.camera.core.ImageCapture
import androidx.camera.core.TorchState
import androidx.camera.video.Quality
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AutoFixHigh
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.FlashAuto
import androidx.compose.material.icons.sharp.FlashOff
import androidx.compose.material.icons.sharp.FlashOn
import androidx.compose.material.icons.sharp.FlipCameraAndroid
import androidx.compose.material.icons.sharp.Forward5
import androidx.compose.material.icons.sharp.Fullscreen
import androidx.compose.material.icons.sharp.FullscreenExit
import androidx.compose.material.icons.sharp.Hd
import androidx.compose.material.icons.sharp.HdrAuto
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.material.icons.sharp.ModeStandby
import androidx.compose.material.icons.sharp.PauseCircle
import androidx.compose.material.icons.sharp.PlayCircle
import androidx.compose.material.icons.sharp.Replay5
import androidx.compose.material.icons.sharp.Sd
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.material.icons.sharp.StopCircle
import androidx.compose.material.icons.sharp.Timer10
import androidx.compose.material.icons.sharp.Timer3
import androidx.compose.material.icons.sharp.TimerOff
import androidx.compose.material.icons.sharp._2k
import androidx.compose.material.icons.sharp._4k
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.gilberto.test.features.media.photo.utils.UtilMedia.TAG
import com.gilberto.test.features.media.photo.utils.UtilMedia.TIMER_10S
import com.gilberto.test.features.media.photo.utils.UtilMedia.TIMER_3S
import com.gilberto.test.features.media.photo.utils.UtilMedia.TIMER_OFF
import java.util.concurrent.TimeUnit

@Preview
@Composable
fun DefaultPreview() {
    CameraRecordIcon(modifier = Modifier, view = LocalView.current) {
    }
}

@Composable
fun CapturedImageThumbnailIcon(
    modifier: Modifier = Modifier,
    imageUri: Uri?,
    rotation: Int,
    onTapped: () -> Unit = {},
) {
    IconButton(
        modifier = Modifier
            .then(modifier),
        onClick = {
        },
        content = {
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = Color.White, shape = CircleShape)
                    .rotate(
                        when (rotation) {
                            Surface.ROTATION_0 -> 0f
                            Surface.ROTATION_90 -> 90f
                            Surface.ROTATION_180 -> 180f
                            Surface.ROTATION_270 -> 270f
                            else -> 0f
                        },
                    ),
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data = imageUri)
                        .crossfade(true)
                        .build(),
                    filterQuality = FilterQuality.Medium,
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        },
    )
}

@Composable
fun CloseVideoThumbnailIcon(
    modifier: Modifier = Modifier,
    onTapped: () -> Unit,
) {
    val context = LocalContext.current
    IconButton(
        modifier = Modifier
            .size(48.dp)
            .padding(1.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
        onClick = {
            onTapped.invoke()
        },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                modifier = modifier.size(60.dp),
                imageVector = Icons.Sharp.Close,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun CameraCaptureIcon(modifier: Modifier = Modifier, view: View, onTapped: () -> Unit) {
    IconButton(
        modifier = Modifier
            .size(64.dp)
            .padding(1.dp)
            .border(1.dp, Color.White, CircleShape),
        onClick = {
            view.vibrate(HapticFeedbackConstants.LONG_PRESS)
            onTapped()
        },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        content = {
            Icon(
                modifier = modifier.size(60.dp),
                imageVector = Icons.Sharp.Lens,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun VideoPauseIcon(modifier: Modifier = Modifier, onTapped: () -> Unit) {
    IconButton(
        modifier = Modifier.then(modifier),
        onClick = { onTapped() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                modifier = Modifier.size(96.dp),
                imageVector = Icons.Sharp.PauseCircle,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun VideoPlayIcon(modifier: Modifier = Modifier, onTapped: () -> Unit) {
    IconButton(
        modifier = Modifier
            .then(modifier),
        onClick = { onTapped() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                modifier = Modifier.size(96.dp),
                imageVector = Icons.Sharp.PlayCircle,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun VideoReplayIcon(modifier: Modifier = Modifier, onTapped: () -> Unit) {
    IconButton(
        modifier = Modifier
            .then(modifier),
        onClick = { onTapped() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Sharp.Replay5,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun VideoForwardIcon(modifier: Modifier = Modifier, onTapped: () -> Unit) {
    IconButton(
        modifier = Modifier
            .then(modifier),
        onClick = { onTapped() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Sharp.Forward5,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun FullScreenToggleIcon(
    modifier: Modifier = Modifier,
    isFullScreen: Boolean,
    onTapped: () -> Unit,
) {
    IconButton(
        modifier = Modifier
            .then(modifier),
        onClick = { onTapped() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = if (isFullScreen) Icons.Sharp.FullscreenExit else Icons.Sharp.Fullscreen,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun CameraPauseIconSmall(modifier: Modifier = Modifier, onTapped: () -> Unit) {
    IconButton(
        modifier = Modifier.then(modifier),
        onClick = { onTapped() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Sharp.PauseCircle,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun CameraPlayIconSmall(rotation: Int, onTapped: () -> Unit) {
    IconButton(
        modifier = Modifier
            .rotate(
                when (rotation) {
                    Surface.ROTATION_0 -> 0f
                    Surface.ROTATION_90 -> 90f
                    Surface.ROTATION_180 -> 180f
                    Surface.ROTATION_270 -> 270f
                    else -> 0f
                },
            ),
        onClick = { onTapped() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Sharp.PlayCircle,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun CameraRecordIcon(modifier: Modifier = Modifier, view: View, onTapped: () -> Unit) {
    IconButton(
        modifier = modifier
            .size(64.dp)
            .padding(1.dp)
            .border(1.dp, Color.White, CircleShape),
        onClick = {
            view.vibrate(HapticFeedbackConstants.LONG_PRESS)
            onTapped()
        },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        content = {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Sharp.ModeStandby,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun CameraStopIcon(modifier: Modifier = Modifier, view: View, onTapped: () -> Unit) {
    IconButton(
        modifier = modifier
            .size(64.dp)
            .padding(1.dp),
        onClick = {
            view.vibrate(HapticFeedbackConstants.LONG_PRESS)
            onTapped()
        },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Sharp.StopCircle,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun CameraFlipIcon(modifier: Modifier = Modifier, view: View, rotation: Int, onTapped: () -> Unit) {
    IconButton(
        modifier = modifier
            .rotate(
                when (rotation) {
                    Surface.ROTATION_0 -> 0f
                    Surface.ROTATION_90 -> 90f
                    Surface.ROTATION_180 -> 180f
                    Surface.ROTATION_270 -> 270f
                    else -> 0f
                },
            ),
        onClick = {
            view.vibrate(HapticFeedbackConstants.LONG_PRESS)
            onTapped()
        },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Sharp.FlipCameraAndroid,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun CameraTorchIcon(
    modifier: Modifier = Modifier,
    showFlashIcon: Boolean,
    @TorchState.State torchState: Int,
    rotation: Int,
    onTapped: () -> Unit,
) {
    IconButton(
        modifier = modifier
            .rotate(
                when (rotation) {
                    Surface.ROTATION_0 -> 0f
                    Surface.ROTATION_90 -> 90f
                    Surface.ROTATION_180 -> 180f
                    Surface.ROTATION_270 -> 270f
                    else -> 0f
                },
            ),
        enabled = showFlashIcon,
        onClick = { onTapped() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                imageVector = if (torchState == TorchState.ON) {
                    Icons.Sharp.FlashOff
                } else {
                    Icons.Sharp.FlashOn
                },
                contentDescription = null,
            )
        },
    )
}

@Composable
fun CameraDelayIcon(
    delayTimer: Int,
    rotation: Int,
    onTapped: () -> Unit,
) {
    IconButton(
        modifier = Modifier
            .rotate(
                when (rotation) {
                    Surface.ROTATION_0 -> 0f
                    Surface.ROTATION_90 -> 90f
                    Surface.ROTATION_180 -> 180f
                    Surface.ROTATION_270 -> 270f
                    else -> 0f
                },
            ),
        onClick = { onTapped() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                imageVector = when (delayTimer) {
                    TIMER_OFF -> Icons.Sharp.TimerOff
                    TIMER_3S -> Icons.Sharp.Timer3
                    TIMER_10S -> Icons.Sharp.Timer10
                    else -> Icons.Sharp.TimerOff
                },
                contentDescription = null,
            )
        },
    )
}

@Composable
fun CameraFlashIcon(
    showFlashIcon: Boolean,
    rotation: Int,
    @ImageCapture.FlashMode flashMode: Int,
    onTapped: () -> Unit,
) {
    IconButton(
        modifier = Modifier
            .rotate(
                when (rotation) {
                    Surface.ROTATION_0 -> 0f
                    Surface.ROTATION_90 -> 90f
                    Surface.ROTATION_180 -> 180f
                    Surface.ROTATION_270 -> 270f
                    else -> 0f
                },
            ),
        onClick = { onTapped() },
        enabled = showFlashIcon,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                imageVector = when (flashMode) {
                    ImageCapture.FLASH_MODE_AUTO -> Icons.Sharp.FlashAuto
                    ImageCapture.FLASH_MODE_OFF -> Icons.Sharp.FlashOff
                    ImageCapture.FLASH_MODE_ON -> Icons.Sharp.FlashOn
                    else -> Icons.Sharp.FlashOff
                },
                contentDescription = null,
            )
        },
    )
}

@Composable
fun CameraEditIcon(rotation: Int, onTapped: () -> Unit) {
    IconButton(
        modifier = Modifier
            .rotate(
                when (rotation) {
                    Surface.ROTATION_0 -> 0f
                    Surface.ROTATION_90 -> 90f
                    Surface.ROTATION_180 -> 180f
                    Surface.ROTATION_270 -> 270f
                    else -> 0f
                },
            ),
        onClick = { onTapped() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                imageVector = Icons.Sharp.AutoFixHigh,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun QualitySelectorIcon(rotation: Int, quality: Quality, onTapped: () -> Unit) {
    IconButton(
        modifier = Modifier
            .rotate(
                when (rotation) {
                    Surface.ROTATION_0 -> 0f
                    Surface.ROTATION_90 -> 90f
                    Surface.ROTATION_180 -> 180f
                    Surface.ROTATION_270 -> 270f
                    else -> 0f
                },
            ),
        onClick = { onTapped() },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                imageVector = when (quality) {
                    Quality.SD -> Icons.Sharp.Sd
                    Quality.HD -> Icons.Sharp.Hd
                    Quality.FHD -> Icons.Sharp._2k
                    Quality.UHD -> Icons.Sharp._4k
                    else -> Icons.Sharp.HdrAuto
                },
                contentDescription = null,
            )
        },
    )
}

@Composable
fun SettingsIcon(rotation: Int, onTapped: () -> Unit) {
    IconButton(
        modifier = Modifier
            .rotate(
                when (rotation) {
                    Surface.ROTATION_0 -> 0f
                    Surface.ROTATION_90 -> 90f
                    Surface.ROTATION_180 -> 180f
                    Surface.ROTATION_270 -> 270f
                    else -> 0f
                },
            ),
        onClick = { },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Icon(
                imageVector = Icons.Sharp.Settings,
                contentDescription = null,
            )
        },
    )
}

@Composable
fun Timer(modifier: Modifier = Modifier, seconds: Int) {
    if (seconds > 0) {
        Box(
            modifier = Modifier
                .padding(vertical = 24.dp),
        ) {
            Text(
                text = DateUtils.formatElapsedTime(seconds.toLong()),
                color = Color.White,
                modifier = Modifier
                    .background(color = Color.Red)
                    .padding(horizontal = 10.dp)
                    .then(modifier),
            )
        }
    }
}

@Composable
fun DelayTimer(millisInFuture: Long) {
    var timeData by remember {
        mutableStateOf(millisInFuture)
    }

    val countDownTimer = object : CountDownTimer(millisInFuture, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            Log.d(TAG, "onTick: ")
            timeData = millisUntilFinished
        }

        override fun onFinish() {
            Log.d(TAG, "Countdown finished.")
        }
    }

    DisposableEffect(key1 = "key1") {
        countDownTimer.start()
        onDispose {
            timeData = millisInFuture
            countDownTimer.cancel()
        }
    }

    Text(
        text = TimeUnit.MILLISECONDS.toSeconds(timeData).toString(),
        textAlign = TextAlign.Center,
    )
}
