package com.gilberto.test.di

import com.gilberto.data.repository.StoriRepositoryImpl
import com.gilberto.data.service.FirebaseService
import com.gilberto.data.service.FirebaseServiceImpl
import com.gilberto.domain.StoriRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindStoriRepository(storiRepository: StoriRepositoryImpl): StoriRepository

    @Binds
    abstract fun bindFirebaseService(firebaseService: FirebaseServiceImpl): FirebaseService
}
