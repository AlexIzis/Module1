package com.example.module1

import com.example.module1.news.NewsUIModel

interface NewsStore {
    fun getNews(): List<NewsUIModel>
}
