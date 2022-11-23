package com.example.module1.search

import kotlinx.coroutines.flow.MutableSharedFlow

class SearchFlow {
    companion object {
        private val flow = MutableSharedFlow<String>()

        fun outputFlow(): MutableSharedFlow<String>{
            return flow
        }
    }
}