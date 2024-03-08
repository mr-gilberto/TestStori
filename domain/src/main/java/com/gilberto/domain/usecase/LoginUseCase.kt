package com.gilberto.domain.usecase

import com.gilberto.domain.StoriRepository
import com.gilberto.domain.models.UserEntity
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: StoriRepository,
) {

    suspend operator fun invoke(params: Params): Result<UserEntity> = try {
        repository.login(params.userName, params.password)
    } catch (exception: Exception) {
        Result.failure(exception)
    }

    data class Params(
        val userName: String,
        val password: String,
    )
}
