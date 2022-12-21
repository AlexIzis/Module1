package com.example.module1.news

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListStringTypeConverter {

    @TypeConverter
    fun listToString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringToList(string: String): List<String> {
        return Gson().fromJson(string, TypeToken.getParameterized(List::class.java, String::class.java).type)
    }
}