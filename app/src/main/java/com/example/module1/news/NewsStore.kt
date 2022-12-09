package com.example.module1.news

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface NewsStore {
    fun getNews(vmScope: CoroutineScope)
    fun getFlow(): Flow<List<NewsUIModel>>
}
