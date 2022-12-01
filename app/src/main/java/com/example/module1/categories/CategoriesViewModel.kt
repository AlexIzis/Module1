package com.example.module1.categories

import androidx.lifecycle.ViewModel

class CategoriesViewModel(store: CategoryStore) : ViewModel() {
    private var _listCategories = store.getList() as ArrayList<CategoryUiModel>
    val listCategories: ArrayList<CategoryUiModel>
        get() = _listCategories
}