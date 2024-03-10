package com.gilberto.data.model

data class MovementData(
    val transactionNumber: String,
    val date: Long,
    val type: Boolean?,
    val description: String?,
    val amount: Double?,
    val balance: Double?,
    val documentId: String,
)
