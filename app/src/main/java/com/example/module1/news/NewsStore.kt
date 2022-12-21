package com.example.module1.news

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface NewsStore {
    fun getDataFromServer(vmScope: CoroutineScope, dispatcher: CoroutineDispatcher)
    fun getFlow(): Flow<List<NewsUIModel>>
    fun getDataFromDB(vmScope: CoroutineScope, dispatcher: CoroutineDispatcher)
}
