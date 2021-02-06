package com.example.core.states

sealed class StarWarResource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : StarWarResource<T>(data)
    class Loading<T>(data: T? = null) : StarWarResource<T>(data)
    class Error<T>(message: String?, data: T? = null) : StarWarResource<T>(data, message)
    class Empty<T> : StarWarResource<T>()
}