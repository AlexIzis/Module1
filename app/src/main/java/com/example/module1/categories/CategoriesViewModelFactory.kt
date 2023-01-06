package com.example.module1.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class CategoriesViewModelFactory @Inject constructor(
    val categoryStore: CategoryStore
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoriesViewModel(categoryStore) as T
    }
}
