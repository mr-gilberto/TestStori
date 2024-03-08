package com.gilberto.data.service

import com.gilberto.data.model.UserAuthData


interface FirebaseService {
    suspend fun login(userName: String, password: String): Result<UserAuthData>
    suspend fun register(userName: String, password: String): Result<UserAuthData>
}
