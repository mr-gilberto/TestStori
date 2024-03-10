package com.gilberto.domain.usecase

import com.gilberto.domain.StoriRepository
import com.gilberto.domain.models.UserEntity
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val storiRepository: StoriRepository,
) {

    suspend operator fun invoke(params: Params): Result<UserEntity> = try {
        storiRepository.updateUserInfo(params.fullName)
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    data class Params(
        val fullName: String,
    )
}
