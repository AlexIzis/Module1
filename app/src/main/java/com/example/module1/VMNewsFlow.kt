package com.example.module1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class VMNewsFlow : ViewModel() {
    companion object {
        val readNews = arrayListOf<Int>()
        private val flow = MutableSharedFlow<Int>()
    }

    fun emitData(id: Int) {
        viewModelScope.launch {
            if (!readNews.contains(id))
                readNews.add(id)
            try {
                flow.emit(readNews.size)
            } catch (e: Exception) {
                Log.d("tag", e.toString())
                Log.d("tag", "Программка, не болей")
            }
        }
    }

    fun collectData(): Int {
        var value = 0
        viewModelScope.launch {
            flow.collect {
                value = it
            }
        }
        return value
    }

    fun getScope(): CoroutineScope {
        return viewModelScope
    }

    fun getFlow(): MutableSharedFlow<Int> {
        return flow
    }
}
