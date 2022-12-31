package com.example.module1.news

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class NewsIntent(private val store: NewsStore) {
    fun actionDataBase(liveCycleScope: CoroutineScope) {
        store.getDataFromDB(liveCycleScope, Dispatchers.IO)
    }
}