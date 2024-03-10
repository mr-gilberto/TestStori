package com.gilberto.domain.usecase

import com.gilberto.domain.StoriRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val storiRepository: StoriRepository,
) {

    suspend operator fun invoke(): Result<Boolean> = try {
        storiRepository.logOut()
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}
