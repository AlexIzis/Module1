package com.example.module1

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class VMNewsFlow : ViewModel() {
    private val readNews = arrayListOf<Int>()
    private val _flow = MutableStateFlow(0)
    val flow: StateFlow<Int> = _flow.asStateFlow()


    fun emitData(id: Int) {
        viewModelScope.launch {
            if (!readNews.contains(id))
                readNews.add(id)
            try {
                _flow.emit(readNews.size)
            } catch (e: Exception) {
                Log.d("tag", e.toString())
                Log.d("tag", "Программка, не болей")
            }
        }
    }
}