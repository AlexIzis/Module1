package com.example.module1.retrofit

import com.example.module1.categories.CategoryUiModel
import retrofit2.Call
import retrofit2.http.GET

interface CategoriesAPI {
    @GET("categories")
    fun getCategoriesList(): Call<MutableList<CategoryUiModel>>
}