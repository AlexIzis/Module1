package com.example.module1.retrofit

import com.example.module1.news.NewsUIModel
import retrofit2.Call
import retrofit2.http.GET

interface NewsAPI {
    @GET("news")
    fun getNewsList(): Call<MutableList<NewsUIModel>>
}
