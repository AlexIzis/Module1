package com.example.module1.retrofit

class Common {
    private val baseUrl = "https://mobile-study.simbirsoft1.com/"
    val retrofitServicesNews: NewsAPI
        get() = Service.getClient(baseUrl).create(NewsAPI::class.java)

    val retrofitServiceCategories: CategoriesAPI
        get() = Service.getClient(baseUrl).create(CategoriesAPI::class.java)
}
