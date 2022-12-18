package com.example.module1.categories

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface CategoryStore {
    fun getDataFromServer(vmScope: CoroutineScope, dispatcher: CoroutineDispatcher)
    fun getFlow(): Flow<List<CategoryUiModel>>
    fun getDataFromDB(vmScope: CoroutineScope, dispatcher: CoroutineDispatcher)
}
