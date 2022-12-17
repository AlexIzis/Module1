package com.example.module1.categories

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface CategoryStore {
    fun getList(vmScope: CoroutineScope)
    fun getFlow(): Flow<List<CategoryUiModel>>
    fun getDataFromDB(context: Context, vmScope: CoroutineScope)
}
