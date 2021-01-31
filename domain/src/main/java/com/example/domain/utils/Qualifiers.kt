package com.example.domain.utils

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteRepository


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalRepository
