package com.example.module1.news

import androidx.lifecycle.ViewModel

class NewsViewModel(store: NewsStore) : ViewModel() {
    private var _listNews = store.getNews() as ArrayList<NewsUIModel>
    val listNews: ArrayList<NewsUIModel>
        get() = _listNews
}
