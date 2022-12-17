package com.example.module1.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.module1.categories.CategoryUiModel

@Database(entities = [CategoryUiModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoriesDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "categories_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}