package com.gilberto.data.mappers

import com.gilberto.data.model.UserAuthData
import com.gilberto.domain.models.UserEntity


fun UserAuthData.toDomain() = UserEntity(
    email = email,
    displayName = displayName,
    uid = uid,
)