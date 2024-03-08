package com.gilberto.test.di

import android.content.Context
import com.gilberto.domain.common.base.AppDispatchers
import com.gilberto.test.features.media.photo.utils.FileManager
import com.gilberto.test.util.NetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Singleton
    @Provides
    fun provideAppDispatchers(): AppDispatchers {
        return AppDispatchers()
    }

    @Provides
    @Singleton
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager {
        return NetworkManager(context)
    }

    @Provides
    fun provideFileManager(@ApplicationContext context: Context): FileManager {
        return FileManager(context)
    }
}
