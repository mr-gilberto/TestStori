package com.gilberto.domain.usecase

import com.gilberto.domain.StoriRepository
import com.gilberto.domain.models.MovementEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveMovementUseCase @Inject constructor(
    private val storiRepository: StoriRepository,
) {
    suspend operator fun invoke(params: Params): Flow<MovementEntity> = storiRepository.observeMovement(params.documentId)

    data class Params(
        val documentId: String,
    )
}

