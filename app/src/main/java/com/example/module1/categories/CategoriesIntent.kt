package com.example.module1.categories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class CategoriesIntent(private val store: CategoryStore) {
    fun actionDataBase(liveCycleScope: CoroutineScope) {
        store.getDataFromDB(liveCycleScope, Dispatchers.IO)
    }
}