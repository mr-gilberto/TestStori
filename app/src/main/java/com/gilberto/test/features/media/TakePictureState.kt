package com.gilberto.test.features.media

import androidx.annotation.StringRes

data class TakePictureState(
    val loading: Boolean = false,
    val errorState: ErrorState? = null,
    val successTakePicture: Boolean = false,
)

data class ErrorState(
    @StringRes val snackError: Int? = null,
    val error: String? = null,
)