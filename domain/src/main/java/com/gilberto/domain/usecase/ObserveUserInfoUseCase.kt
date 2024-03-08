package com.gilberto.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ObserveUserInfoUseCase @Inject constructor(
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return flow { true }
    }
}
