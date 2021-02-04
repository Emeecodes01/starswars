package com.example.core.base

interface BaseMapper<FROM, TO> {
    fun mapTo(to: TO): FROM
    fun mapFrom(from: FROM): TO

    fun safeString(string: String?) = string ?: ""

    fun safeString(value: Int?): String = value?.toString() ?: ""

    fun safeBoolean(value: Boolean?) = value ?: false

    fun <T> safeList(value: List<T>?) = value ?: emptyList()

    fun safeInt(value: Int?) = value ?: 0

    fun safeLong(value: Long?) = value ?: 0L

    fun safeDouble(value: Double?) = value ?: 0.0

    fun safeInt(value: String?): Int = if (value.isNullOrEmpty()) 0 else value.toInt()
}