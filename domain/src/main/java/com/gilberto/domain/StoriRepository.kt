package com.gilberto.domain

import com.gilberto.domain.models.MovementEntity
import com.gilberto.domain.models.UserEntity
import kotlinx.coroutines.flow.Flow


interface StoriRepository {
    suspend fun login(email: String, password: String): Result<UserEntity>
    suspend fun register(email: String, password: String): Result<UserEntity>
    suspend fun updateUserInfo(fullName: String): Result<UserEntity>
    suspend fun uploadImage(imageUri: String): Result<Boolean>
    suspend fun getCurrentUser(): Result<UserEntity>
    suspend fun observeMovements(): Flow<List<MovementEntity>>
    suspend fun observeMovement(documentId: String): Flow<MovementEntity>
    suspend fun logOut(): Result<Boolean>
}
