package com.example.module1.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.module1.categories.CategoryUiModel
import com.example.module1.news.ListStringTypeConverter
import com.example.module1.news.NewsUIModel

@Database(entities = [CategoryUiModel::class, NewsUIModel::class], version = 2, exportSchema = false)
@TypeConverters(ListStringTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoriesDao

    abstract fun newDao(): NewsDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "categories_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
