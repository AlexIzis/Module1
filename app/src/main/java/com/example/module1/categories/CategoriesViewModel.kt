package com.example.module1.categories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel(private val store: CategoryStore, private val context: Context) :
    ViewModel() {
    private var _categoriesFlow = MutableStateFlow<List<CategoryUiModel>>(emptyList())
    val categoriesFlow: StateFlow<List<CategoryUiModel>> = _categoriesFlow.asStateFlow()

    fun emitCategoriesList() {
        viewModelScope.launch {
            store.getDataFromDB(context)
            store.getList(viewModelScope)
            store.getFlow().collect {
                _categoriesFlow.emit(it)
            }
        }
    }
}
