package com.example.remote.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit

fun gson(block: GsonBuilder.() -> Unit): Gson {
    return GsonBuilder()
        .apply(block)
        .create()
}

fun retrofit(block: Retrofit.Builder.() -> Unit): Retrofit {
    return Retrofit.Builder()
        .apply(block)
        .build()
}

fun okhttp(block: OkHttpClient.Builder.() -> Unit): OkHttpClient {
    return OkHttpClient.Builder()
        .apply(block)
        .build()
}

inline fun <reified T> service(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}