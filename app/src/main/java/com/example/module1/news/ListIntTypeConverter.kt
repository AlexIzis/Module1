package com.example.module1.news

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListIntTypeConverter {
    @TypeConverter
    fun listToString(list: List<Int>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToList(string: String): List<Int> {
        return Gson().fromJson(string, TypeToken.getParameterized(List::class.java, Int::class.java).type)
    }
}