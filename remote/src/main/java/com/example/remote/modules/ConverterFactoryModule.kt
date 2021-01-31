package com.example.remote.modules

import com.example.remote.utils.gson
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ConverterFactoryModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return gson { }
    }

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }
}