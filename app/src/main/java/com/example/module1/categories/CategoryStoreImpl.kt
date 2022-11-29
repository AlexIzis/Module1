package com.example.module1.categories

class CategoryStoreImpl: CategoryStore {
    private var listCategories = arrayListOf(
        CategoryUiModel("@drawable/little", "Дети", "children"),
        CategoryUiModel("@drawable/dad", "Взрослые", "adults"),
        CategoryUiModel("@drawable/granny", "Пожилые", "elderly"),
        CategoryUiModel("@drawable/cat", "Животные", "animals"),
        CategoryUiModel("@drawable/event", "События", "events")
    )

    override fun getList(): List<CategoryUiModel> = listCategories
}
