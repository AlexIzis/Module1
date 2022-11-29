package com.example.module1.news

import kotlinx.coroutines.flow.MutableSharedFlow

class NewsFlow {
    companion object {
        val readNews = arrayListOf<Int>()
        private val flow = MutableSharedFlow<Int>()

        fun outputData(): MutableSharedFlow<Int> {
            return flow
        }
    }
}