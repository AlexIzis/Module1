package com.example.module1.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.module1.CategoryStore

class CategoriesViewModelFactory(private val categoryStore: CategoryStore) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoriesViewModel(categoryStore) as T
    }
}
