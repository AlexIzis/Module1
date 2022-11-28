package com.example.module1

import com.example.module1.categories.CategoryUiModel

class CategoryStore {
    var listCategories = arrayListOf(
        CategoryUiModel("@drawable/little", "Дети", "children"),
        CategoryUiModel("@drawable/dad", "Взрослые", "adults"),
        CategoryUiModel("@drawable/granny", "Пожилые", "elderly"),
        CategoryUiModel("@drawable/cat", "Животные", "animals"),
        CategoryUiModel("@drawable/event", "События", "events")
    )
}