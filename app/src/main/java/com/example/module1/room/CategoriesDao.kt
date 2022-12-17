package com.example.module1.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.module1.categories.CategoryUiModel

@Dao
interface CategoriesDao  {
    @Query("SELECT * FROM category_table")
    fun getCategories(): List<CategoryUiModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategory(cat: CategoryUiModel)

    @Query("DELETE FROM category_table")
    fun deleteAllCategories()
}