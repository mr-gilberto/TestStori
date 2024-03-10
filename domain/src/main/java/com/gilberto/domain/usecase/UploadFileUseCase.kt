package com.gilberto.domain.usecase

import com.gilberto.domain.StoriRepository
import javax.inject.Inject

class UploadFileUseCase @Inject constructor(
    private val storiRepository: StoriRepository,
) {

    suspend operator fun invoke(params: Params): Result<Boolean> = try {
        storiRepository.uploadImage(params.imageUri)
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    data class Params(
        val imageUri: String,
    )
}
