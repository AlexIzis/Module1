package com.example.module1.news

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface NewsStore {
    fun getDataFromServer(scope: CoroutineScope, dispatcher: CoroutineDispatcher)
    fun getFlow(): Flow<List<NewsUIModel>>
    fun getDataFromDB(scope: CoroutineScope, dispatcher: CoroutineDispatcher)
}
