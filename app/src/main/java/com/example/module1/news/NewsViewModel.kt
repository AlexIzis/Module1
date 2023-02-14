package com.example.module1.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val store: NewsStore) : ViewModel() {
    private var _newsFlow = MutableStateFlow<List<NewsUIModel>>(emptyList())
    val newsFlow: StateFlow<List<NewsUIModel>> = _newsFlow.asStateFlow()

    fun handleIntent(intent: NewsIntent) {
        when (intent) {
            is NewsIntent.StartScreen -> {
                store.getDataFromDB(viewModelScope, Dispatchers.IO)
            }
        }
    }

    fun emitNewsList() {
        viewModelScope.launch {
            store.getFlow().collect {
                _newsFlow.emit(it)
            }
        }
    }

    fun filterByCategories(categories: ArrayList<String>) {
        if (categories.isNotEmpty()) {
            viewModelScope.launch {
                _newsFlow.emit(
                    categories.flatMap { cat ->
                        newsFlow.value.filter { new ->
                            new.categories.contains(
                                cat
                            )
                        }
                    }
                )
            }
        }
    }
}
