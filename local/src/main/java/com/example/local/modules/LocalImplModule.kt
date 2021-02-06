package com.example.local.modules

import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.utils.LocalRepository
import com.example.local.impl.StarWarsLocalDataRepositoryImpl
import com.example.local.preference.IStarWarsPreferenceManager
import com.example.local.preference.StarWarsPreferenceManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalImplModule {

    @Binds
    @Singleton
    abstract fun bindPreference(
        impl: StarWarsPreferenceManager
    ): IStarWarsPreferenceManager


    @Binds
    @LocalRepository
    abstract fun bindLocalRepositoryImpl(
        impl: StarWarsLocalDataRepositoryImpl
    ): StarWarsDataRepository

}