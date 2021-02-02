package com.example.remote.modules

import com.example.domain.repository.StarWarsDataRepository
import com.example.domain.utils.RemoteRepository
import com.example.remote.impl.StarWarsRemoteDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteImplModule {

    @RemoteRepository
    @Binds
    abstract fun bindRemoteStarWarsRepositoryImpl(
        impl: StarWarsRemoteDataRepositoryImpl
    ): StarWarsDataRepository


}