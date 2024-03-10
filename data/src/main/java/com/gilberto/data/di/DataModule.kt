package com.gilberto.test.di

import com.gilberto.data.repository.StoriRepositoryImpl
import com.gilberto.data.service.FirebaseService
import com.gilberto.data.service.FirebaseServiceImpl
import com.gilberto.domain.StoriRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindStoriRepository(storiRepository: StoriRepositoryImpl): StoriRepository

    @Binds
    abstract fun bindFirebaseService(firebaseService: FirebaseServiceImpl): FirebaseService

    companion object {

        @Provides
        fun provideFirebaseDatabase(): FirebaseFirestore {
            return Firebase.firestore
        }

        @Provides
        fun provideFirebaseAuth(): FirebaseAuth {
            return FirebaseAuth.getInstance()
        }

        @Provides
        fun provideFirebaseStorage(): FirebaseStorage {
            return Firebase.storage
        }
    }
}
