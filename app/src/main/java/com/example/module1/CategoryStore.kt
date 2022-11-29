package com.example.module1

import com.example.module1.categories.CategoryUiModel

interface CategoryStore {
    fun getList(): List<CategoryUiModel>
}
