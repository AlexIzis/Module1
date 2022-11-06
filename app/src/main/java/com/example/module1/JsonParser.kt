package com.example.module1

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.lang.reflect.Type

class JsonParser<T>(
    private val jsonFileName: String,
    private val classType: Class<T>?,
    val context: Context
) {
    fun parseJson(): List<T> {
        return try {
            val inputDateString =
                context.assets.open(jsonFileName).bufferedReader().use { it.readText() }
            val type: Type = TypeToken.getParameterized(List::class.java, classType).type
            Gson().fromJson(inputDateString, type)
        } catch (ioException: IOException){
            ioException.printStackTrace()
            emptyList()
        }
    }
}