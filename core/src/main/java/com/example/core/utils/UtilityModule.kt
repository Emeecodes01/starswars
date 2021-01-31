package com.example.core.utils

import com.example.domain.thread.ExecutionThread
import com.example.domain.thread.ExecutionThreadImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class UtilityModule {

    @Provides
    @Singleton
    fun provideThreadImpl(): ExecutionThread = ExecutionThreadImpl()
}