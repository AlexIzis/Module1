package com.example.module1.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.module1.news.NewsUIModel

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    fun getNews(): List<NewsUIModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(new: NewsUIModel)

    @Query("DELETE FROM news_table")
    fun deleteAllNews()
}