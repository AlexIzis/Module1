package com.example.module1.categories

import androidx.lifecycle.ViewModel

class CategoriesViewModel(list: ArrayList<CategoryUiModel>) : ViewModel() {
    private var _listCategories = list
    val listCategories: ArrayList<CategoryUiModel>
        get() = _listCategories
}