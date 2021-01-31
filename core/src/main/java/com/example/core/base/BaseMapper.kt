package com.example.core.base

interface BaseMapper<FROM, TO> {
    fun mapTo(to: TO): FROM
    fun mapFrom(from: FROM): TO
}