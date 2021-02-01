package com.example.core.utils

import com.example.domain.thread.ExecutionThread
import com.example.domain.thread.ExecutionThreadImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class UtilityModule {

    @Binds
    @Singleton
    abstract fun provideThreadImpl(
        impl: ExecutionThreadImpl
    ): ExecutionThread

}