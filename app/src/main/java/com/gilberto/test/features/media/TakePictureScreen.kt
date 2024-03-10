package com.gilberto.test.features.media


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import com.gilberto.test.BuildConfig
import com.gilberto.test.R
import com.gilberto.test.features.register.ErrorState
import com.gilberto.test.features.register.LoadingDialog
import com.gilberto.test.features.register.Logo
import com.gilberto.test.features.view.SnackBarBase
import com.gilberto.test.theme.Colors
import java.io.File
import java.util.Objects


@Composable
fun TakePictureScreen(
    uploadFile: (String) -> Unit = {},
    isLoading: Boolean = false,
    errorState: ErrorState? = null,
) {
    Scaffold(
        snackbarHost = {
            errorState?.let { state ->
                val error = state.error ?: state.snackError?.let { stringResource(id = it) }
                if (error != null) {
                    SnackBarBase(error) {
                    }
                }
            }
        },
        content = { innerPadding ->
            Box {
                Modifier.padding(innerPadding)
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(
                            15.dp,
                            60.dp,
                            15.dp,
                            0.dp,
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Logo(
                        Modifier
                            .height(90.dp)
                            .fillMaxWidth(),
                    )

                    ImageCapture(uploadFile)
                }

                if (isLoading) {
                    LoadingDialog()
                }
            }
        },
    )
}


@Composable
fun ImageCapture(uploadFile: (String) -> Unit) {
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider", file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        capturedImageUri = uri
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, R.string.require_permissions, Toast.LENGTH_SHORT).show()
        }
    }

    Spacer(
        modifier = Modifier
            .width(5.dp)
            .height(14.dp),
    )

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Colors.ColorSecondary),
        ) {
            Spacer(
                modifier = Modifier
                    .width(5.dp)
                    .height(34.dp),
            )
            Text(text = stringResource(R.string.require_picture), color = Color.White)

        }
    }

    Spacer(
        modifier = Modifier
            .width(5.dp)
            .height(14.dp),
    )

    Button(onClick = {
        val permissionCheckResult = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
            cameraLauncher.launch(uri)
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }) {
        Text(text = stringResource(R.string.capture_image_from_camera))
    }


    val path = capturedImageUri.path
    if (path?.isNotEmpty() == true) {

        Button(
            onClick = {
                uploadFile.invoke(capturedImageUri.toString())
            },
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.default_padding_buttons),
                )
                .fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.send_picture),
                style = MaterialTheme.typography.bodyMedium,
            )
        }

        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 8.dp),
            painter = rememberAsyncImagePainter(capturedImageUri),
            contentDescription = null
        )
    }
}

fun Context.createImageFile(): File {
    val imageFileName = "JPEG_" + "temp" + "_"
    return File.createTempFile(imageFileName, ".jpg", externalCacheDir)
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TakePictureScreen()
}

