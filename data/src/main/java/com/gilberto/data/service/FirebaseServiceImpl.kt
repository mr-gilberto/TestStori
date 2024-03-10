package com.gilberto.data.service

import androidx.core.net.toUri
import com.gilberto.data.model.MovementData
import com.gilberto.data.model.UserAuthData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine

class FirebaseServiceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseAuth: FirebaseAuth,
) : FirebaseService {
    override suspend fun login(userName: String, password: String): Result<UserAuthData> = suspendCancellableCoroutine { continuation ->
        firebaseAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseAuth.currentUser?.run {
                    continuation.resume(
                        Result.success(
                            UserAuthData(email, displayName, uid)
                        )
                    )
                } ?: {
                    continuation.resumeWithException(task.exception ?: Exception(UNKNOWN_ERROR_MESSAGE))
                }
            } else {
                continuation.resumeWithException(task.exception ?: Exception(UNKNOWN_ERROR_MESSAGE))
            }
        }
    }

    override suspend fun updateUserInfo(fullName: String) = suspendCancellableCoroutine { continuation ->
        firebaseAuth.currentUser?.let {
            val profileUpdates = userProfileChangeRequest {
                displayName = fullName
            }

            it.updateProfile(profileUpdates).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseAuth.currentUser?.run {
                        continuation.resume(
                            Result.success(
                                UserAuthData(email, displayName, uid)
                            )
                        )
                    } ?: {
                        continuation.resumeWithException(task.exception ?: Exception(UNKNOWN_ERROR_MESSAGE))
                    }
                } else {
                    continuation.resumeWithException(task.exception ?: Exception(UNKNOWN_ERROR_MESSAGE))
                }
            }.addOnCanceledListener {
                continuation.resumeWithException(Exception(UNKNOWN_ERROR_MESSAGE))
            }
        }
    }

    override suspend fun register(userName: String, password: String): Result<UserAuthData> = suspendCancellableCoroutine { continuation ->
        firebaseAuth.createUserWithEmailAndPassword(userName, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseAuth.currentUser?.run {
                    continuation.resume(Result.success(UserAuthData(email, displayName, uid)))
                } ?: {
                    continuation.resumeWithException(task.exception ?: Exception(UNKNOWN_ERROR_MESSAGE))
                }
            } else {
                continuation.resumeWithException(task.exception ?: Exception(UNKNOWN_ERROR_MESSAGE))
            }
        }
    }

    override suspend fun uploadFile(imageUri: String) = suspendCancellableCoroutine { continuation ->
        val fileReference = firebaseStorage.reference.child("$FILES_STORAGE_PATH/${firebaseAuth.currentUser?.uid}")
        val uploadTask: UploadTask = fileReference.putFile(imageUri.toUri())
        uploadTask.addOnSuccessListener {
            continuation.resume(Result.success(true))
        }.addOnFailureListener { task ->
            continuation.resumeWithException(task.cause ?: Exception(UNKNOWN_ERROR_MESSAGE))
        }
    }

    override suspend fun getCurrentUser(): Result<UserAuthData> {
        val user = firebaseAuth.currentUser
        return if (user != null) {
            Result.success(UserAuthData(user.email, user.displayName, user.uid))
        } else {
            Result.failure(Exception(USER_NOT_FOUND_ERROR_MESSAGE))
        }
    }

    override suspend fun observeUserMovements(): Flow<List<MovementData>> = callbackFlow {
        val user = firebaseAuth.currentUser ?: throw Exception(USER_NOT_FOUND_ERROR_MESSAGE)
        val reference = firebaseFirestore.collection("users").document(user.uid).collection("movements")

        val listener = reference.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception)
                return@addSnapshotListener
            }

            val movements = mutableListOf<MovementData>()

            if (snapshot != null && !snapshot.isEmpty) {
                for (document in snapshot.documents) {
                    val transactionNumber = document.getString(TRANSACTION_NUMBER_FIELD) ?: ""
                    val date = document.getTimestamp(DATE_FIELD)?.toDate()?.time ?: 0
                    val type = document.getBoolean(TYPE_FIELD) ?: false
                    val description = document.getString(DESCRIPTION_FIELD) ?: ""
                    val amount = document.getDouble(AMOUNT_FIELD) ?: 0.0
                    val balance = document.getDouble(BALANCE_FIELD) ?: 0.0

                    val movement = MovementData(transactionNumber, date, type, description, amount, balance, document.id)
                    movements.add(movement)
                }
            }

            trySend(movements)
        }

        awaitClose {
            listener.remove()
        }
    }

    override suspend fun observeUserMovement(movementId: String): Flow<MovementData> = callbackFlow {
        val user = firebaseAuth.currentUser ?: throw Exception(USER_NOT_FOUND_ERROR_MESSAGE)

        val reference = firebaseFirestore.collection("users").document(user.uid).collection("movements").document(movementId)

        val listener = reference.addSnapshotListener { snapshot, exception ->
            if (exception != null) {
                close(exception)
                return@addSnapshotListener
            }

            val movement = if (snapshot != null && snapshot.exists()) {
                val transactionNumber = snapshot.getString(TRANSACTION_NUMBER_FIELD) ?: ""
                val date = snapshot.getTimestamp(DATE_FIELD)?.toDate()?.time ?: 0
                val type = snapshot.getBoolean(TYPE_FIELD) ?: false
                val description = snapshot.getString(DESCRIPTION_FIELD) ?: ""
                val amount = snapshot.getDouble(AMOUNT_FIELD) ?: 0.0
                val balance = snapshot.getDouble(BALANCE_FIELD) ?: 0.0

                MovementData(transactionNumber, date, type, description, amount, balance, snapshot.id)
            } else {
                null
            }

            if (movement != null) {
                trySend(movement)
            } else {
                close(Exception("Error empty"))
            }
        }

        awaitClose {
            listener.remove()
        }
    }

    override suspend fun logOut(): Result<Boolean> =
        suspendCancellableCoroutine { continuation ->
            firebaseAuth.signOut()
            var authListener: FirebaseAuth.AuthStateListener? = null
            authListener = FirebaseAuth.AuthStateListener {
                if (continuation.isActive) {
                    authListener?.let { auth -> firebaseAuth.removeAuthStateListener(auth) }
                    if (firebaseAuth.currentUser == null) {
                        continuation.resume(Result.success(true))
                    } else {
                        continuation.resume(Result.success(false))
                    }
                }
            }

            firebaseAuth.addAuthStateListener(authListener)
        }

    companion object {
        private const val UNKNOWN_ERROR_MESSAGE = "Unknown Error"
        private const val USER_NOT_FOUND_ERROR_MESSAGE = "User Not Found"
        private const val FILES_STORAGE_PATH = "files"
        private const val TRANSACTION_NUMBER_FIELD = "transactionNumber"
        private const val DATE_FIELD = "date"
        private const val TYPE_FIELD = "type"
        private const val DESCRIPTION_FIELD = "description"
        private const val AMOUNT_FIELD = "amount"
        private const val BALANCE_FIELD = "balance"
    }
}
