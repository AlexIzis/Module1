package com.example.module1.categories

import androidx.lifecycle.ViewModel
import com.example.module1.CategoryStore

class CategoriesViewModel : ViewModel() {
    private var _listCategories = CategoryStore().listCategories
    val listCategories: ArrayList<CategoryUiModel>
        get() = _listCategories
}