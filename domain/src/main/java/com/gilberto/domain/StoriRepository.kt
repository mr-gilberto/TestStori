package com.gilberto.domain

import com.gilberto.domain.models.UserEntity


interface StoriRepository {
    suspend fun login(email: String, password: String): Result<UserEntity>
    suspend fun register(email: String, password: String): Result<UserEntity>
}
