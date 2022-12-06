package com.example.module1.news

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewsViewModel(store: NewsStore) : ViewModel() {
    private var _listNews = store.getNews() as ArrayList<NewsUIModel>
    val listNews: ArrayList<NewsUIModel>
        get() = _listNews

    private var _newsFlow = MutableStateFlow<List<NewsUIModel>>(emptyList())
    val newsFlow: StateFlow<List<NewsUIModel>> = _newsFlow.asStateFlow()
}
