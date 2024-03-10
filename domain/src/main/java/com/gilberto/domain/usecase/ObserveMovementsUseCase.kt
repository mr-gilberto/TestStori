package com.gilberto.domain.usecase

import com.gilberto.domain.StoriRepository
import com.gilberto.domain.models.MovementEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveMovementsUseCase @Inject constructor(
    private val storiRepository: StoriRepository,
) {
    suspend operator fun invoke(): Flow<List<MovementEntity>> =
        storiRepository.observeMovements()

}

