package com.example.core.utils.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun<T> List<T>.toFlow(): Flow<T>  = flow {
    forEach { emit(it) }
}
