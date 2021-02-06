package com.example.local.modules

import com.example.local.database.StarWarsDatabase
import com.example.local.database.daos.RecentCharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    @Singleton
    fun provideRecentCharacterDao(database: StarWarsDatabase): RecentCharacterDao
    = database.recentDao()
}