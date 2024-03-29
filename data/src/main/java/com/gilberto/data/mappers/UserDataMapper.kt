package com.gilberto.data.mappers

import com.gilberto.data.model.MovementData
import com.gilberto.data.model.UserAuthData
import com.gilberto.domain.models.MovementEntity
import com.gilberto.domain.models.UserEntity


fun UserAuthData.toDomain() = UserEntity(
    email = email,
    displayName = displayName,
    uid = uid,
)

fun MovementData.toDomain() = MovementEntity(
    transactionNumber = transactionNumber,
    date = date,
    type = type ?: false,
    description = description  ?: "",
    amount = amount?: 0.0,
    balance = balance?: 0.0,
    documentId = documentId
)