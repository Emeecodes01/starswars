package com.example.local.modules

import android.content.Context
import com.example.local.database.StarWarsDatabase
import com.example.local.utils.StarWarsLocalConstants
import com.example.local.utils.database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): StarWarsDatabase =
        database(context, StarWarsLocalConstants.DB_NAME) {}
}