package com.gilberto.data.service


import com.gilberto.data.model.UserAuthData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.suspendCancellableCoroutine


class FirebaseServiceImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseAuth: FirebaseAuth,
) : FirebaseService {

    override suspend fun login(userName: String, password: String): Result<UserAuthData> = suspendCancellableCoroutine { continuation ->
        firebaseAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseAuth.currentUser?.run {
                    continuation.resume(Result.success(
                        UserAuthData(email, displayName, uid))
                    )
                } ?: {
                    continuation.resumeWithException(task.exception ?: Exception("Unkown Error"))
                }
            } else {
                continuation.resumeWithException(task.exception ?: Exception("Unkown Error"))
            }
        }
    }

    fun getAdress() {/*  suspendCancellableCoroutine { continuation ->
              val reference = firebaseDatabase.getReference(USER_KEY)
              val listener = object : ValueEventListener {
                  override fun onDataChange(snapshot: DataSnapshot) {
                      try {
                          val user = snapshot.getValue(UserData::class.java)
                          if (user != null) {
                              continuation.resume(Result.success(user))
                          } else {
                              continuation.resumeWithException(Exception("wrong info"))
                          }
                      } catch (exception: Exception) {
                          continuation.resumeWithException(exception)
                      }
                  }

                  override fun onCancelled(error: DatabaseError) {
                      continuation.resumeWithException(error.toException())
                  }
              }

              continuation.invokeOnCancellation { reference.removeEventListener(listener) }
              reference.addListenerForSingleValueEvent(listener)*/
    }

    override suspend fun register(userName: String, password: String): Result<UserAuthData> = suspendCancellableCoroutine { continuation ->
        firebaseAuth.createUserWithEmailAndPassword(userName, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseAuth.currentUser?.run {
                    continuation.resume(Result.success(UserAuthData(email, displayName, uid)))
                } ?: {
                    continuation.resumeWithException(task.exception ?: Exception("Unkown Error"))
                }
            } else {
                continuation.resumeWithException(task.exception ?: Exception("Unkown Error"))
            }
        }
    }

    companion object {
        const val USER_KEY = "User"
    }
}
