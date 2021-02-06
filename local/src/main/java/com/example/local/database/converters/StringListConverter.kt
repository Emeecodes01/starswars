package com.example.local.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromListToString(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toListFromString(stringValue: String): List<String> {
        val typeToken = object: TypeToken<List<String>>(){}.type
        return gson.fromJson(stringValue, typeToken)
    }

}
