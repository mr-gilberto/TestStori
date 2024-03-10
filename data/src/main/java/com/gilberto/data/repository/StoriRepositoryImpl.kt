package com.gilberto.data.repository

import com.gilberto.data.mappers.toDomain
import com.gilberto.data.service.FirebaseService
import com.gilberto.domain.StoriRepository
import com.gilberto.domain.models.MovementEntity
import com.gilberto.domain.models.UserEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoriRepositoryImpl @Inject constructor(
    private val firebaseSerivce: FirebaseService,
) : StoriRepository {

    override suspend fun login(email: String, password: String): Result<UserEntity> =
        firebaseSerivce.login(email, password).map {
            it.toDomain()
        }

    override suspend fun register(email: String, password: String): Result<UserEntity> =
        firebaseSerivce.register(email, password).map {
            it.toDomain()
        }

    override suspend fun updateUserInfo(fullName: String): Result<UserEntity> =
        firebaseSerivce.updateUserInfo(fullName).map {
            it.toDomain()
        }

    override suspend fun uploadImage(imageUri: String): Result<Boolean> =
        firebaseSerivce.uploadFile(imageUri)

    override suspend fun getCurrentUser(): Result<UserEntity> =
        firebaseSerivce.getCurrentUser().map {
            it.toDomain()
        }

    override suspend fun observeMovements(): Flow<List<MovementEntity>> =
        firebaseSerivce.observeUserMovements().map {
            it.map {
                it.toDomain()
            }
        }

    override suspend fun observeMovement(documentId: String): Flow<MovementEntity> =
        firebaseSerivce.observeUserMovement(documentId).map {
            it.toDomain()
        }

    override suspend fun logOut(): Result<Boolean> {
        return firebaseSerivce.logOut()
    }
}