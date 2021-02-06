package com.example.local.utils

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

inline fun <reified T : RoomDatabase> database(
    context: Context,
    name: String,
    block: RoomDatabase.Builder<T>.() -> Unit
): T {
    return Room.databaseBuilder(context, T::class.java, name)
        .apply(block).build()
}
