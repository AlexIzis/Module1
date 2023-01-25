package com.example.module1.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.module1.categories.CategoryStore
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class FilterViewModelFactory @Inject constructor(val categoryStore: CategoryStore) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FilterViewModel(categoryStore) as T
    }
}