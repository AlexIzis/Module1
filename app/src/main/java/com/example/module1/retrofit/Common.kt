package com.example.module1.retrofit

object Common {
    private const val BASE_URL = "https://mobile-study.simbirsoft1.com/"
    val retrofitServices: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}