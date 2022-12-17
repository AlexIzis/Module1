package com.example.module1.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val store: CategoryStore,
) :
    ViewModel() {
    private var _categoriesFlow = MutableStateFlow<List<CategoryUiModel>>(emptyList())
    val categoriesFlow: StateFlow<List<CategoryUiModel>> = _categoriesFlow.asStateFlow()

    fun emitCategoriesList() {
        viewModelScope.launch {
            store.getDataFromDB(viewModelScope, Dispatchers.IO)
            store.getFlow().collect {
                _categoriesFlow.emit(it)
            }
        }
    }
}
