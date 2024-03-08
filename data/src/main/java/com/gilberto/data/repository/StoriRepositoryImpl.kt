package com.gilberto.data.repository

import com.gilberto.data.mappers.toDomain
import com.gilberto.data.service.FirebaseService
import com.gilberto.domain.StoriRepository
import com.gilberto.domain.models.UserEntity
import javax.inject.Inject


class StoriRepositoryImpl @Inject constructor(
    private val firebaseServiceImpl: FirebaseService,
) : StoriRepository {

    override suspend fun login(email: String, password: String): Result<UserEntity> =
        firebaseServiceImpl.login(email, password).map {
            it.toDomain()
        }

    override suspend fun register(email: String, password: String): Result<UserEntity> =
        firebaseServiceImpl.register(email, password).map {
            it.toDomain()
        }
}