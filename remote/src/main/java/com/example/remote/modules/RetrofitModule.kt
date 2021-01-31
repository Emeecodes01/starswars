package com.example.remote.modules

import com.example.remote.BuildConfig
import com.example.remote.utils.retrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: GsonConverterFactory
    ): Retrofit {
        return retrofit {
            baseUrl(BuildConfig.BASE_URL)
            addConverterFactory(gson)
            client(okHttpClient)
        }
    }
}