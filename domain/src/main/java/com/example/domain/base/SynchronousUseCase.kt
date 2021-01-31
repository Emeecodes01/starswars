package com.example.domain.base

abstract class SynchronousUseCase<in Params, T> {
    abstract fun execute(params: Params? = null): T
}
