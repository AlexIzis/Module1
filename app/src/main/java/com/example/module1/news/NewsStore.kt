package com.example.module1.news

import kotlinx.coroutines.flow.Flow

interface NewsStore {
    fun getNews()
    fun getFlow(): Flow<List<NewsUIModel>>
}
