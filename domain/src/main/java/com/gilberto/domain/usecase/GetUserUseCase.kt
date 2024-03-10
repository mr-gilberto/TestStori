package com.gilberto.domain.usecase

import com.gilberto.domain.StoriRepository
import com.gilberto.domain.models.UserEntity
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val storiRepository: StoriRepository,
) {

    suspend operator fun invoke(): Result<UserEntity> = try {
        storiRepository.getCurrentUser()
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}
