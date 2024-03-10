package com.gilberto.data.service

import com.gilberto.data.model.MovementData
import com.gilberto.data.model.UserAuthData
import kotlinx.coroutines.flow.Flow


interface FirebaseService {
    suspend fun login(userName: String, password: String): Result<UserAuthData>
    suspend fun register(userName: String, password: String): Result<UserAuthData>
    suspend fun updateUserInfo(fullName: String): Result<UserAuthData>
    suspend fun uploadFile(imageUri: String): Result<Boolean>
    suspend fun getCurrentUser(): Result<UserAuthData>
    suspend fun observeUserMovements(): Flow<List<MovementData>>
}
