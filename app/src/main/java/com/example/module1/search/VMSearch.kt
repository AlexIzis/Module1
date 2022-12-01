package com.example.module1.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VMSearch : ViewModel() {
    companion object {
        private val _flow = MutableStateFlow("...")
    }

    val flow: StateFlow<String> = _flow.asStateFlow()

    fun searchResult(string: String) {
        viewModelScope.launch {
            try {
                _flow.emit(string)
            } catch (e: Exception) {
                Log.d("tag", e.toString())
                Log.d("tag", "Программка, не болей")
            }
        }
    }
}