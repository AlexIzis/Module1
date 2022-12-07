package com.example.module1.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(store: NewsStore) : ViewModel() {
    private var _newsFlow = MutableStateFlow<List<NewsUIModel>>(emptyList())
    val newsFlow: StateFlow<List<NewsUIModel>> = _newsFlow.asStateFlow()

    fun emitNewsList(listNews: List<NewsUIModel>) {
        viewModelScope.launch {
            _newsFlow.emit(listNews)
        }
    }
}
