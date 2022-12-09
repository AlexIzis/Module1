package com.example.module1.retrofit

object Common {
    private const val BASE_URL = "https://mobile-study.simbirsoft1.com/"
    val retrofitServices: NewsAPI
        get() = NewsService.getClient(BASE_URL).create(NewsAPI::class.java)
}
